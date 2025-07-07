package com.empresa.empresa.Services.Payments;

import com.empresa.empresa.Models.Authentication.Users;
import com.empresa.empresa.Models.Cart.Cart;
import com.empresa.empresa.Models.Cart.CartItems;
import com.empresa.empresa.Models.Payments.Payment;
import com.empresa.empresa.Models.Payments.PaymentStatus;
import com.empresa.empresa.Models.Products.Products;
import com.empresa.empresa.Repositories.Authentication.UsersRepository;
import com.empresa.empresa.Repositories.Cart.CartRepository;
import com.empresa.empresa.Repositories.Payments.PaymentRepository;
import com.empresa.empresa.Repositories.Products.ProductsRepository;
import com.empresa.empresa.Services.Mails.EmailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final CartRepository cartRepository;
    private final UploadedPaymentFileService fileService;
    private final UsersRepository usersRepository;
    private final EmailService emailService;
    private final ProductsRepository productRepository;

    public PaymentService(PaymentRepository paymentRepository,
                          CartRepository cartRepository,
                          UploadedPaymentFileService fileService,
                          UsersRepository usersRepository,
                          EmailService emailService,
                          ProductsRepository productRepository) {
        this.paymentRepository = paymentRepository;
        this.cartRepository = cartRepository;
        this.fileService = fileService;
        this.usersRepository = usersRepository;
        this.emailService = emailService;
        this.productRepository = productRepository;
    }

    public void registerPayment(Integer cartId, String reference, MultipartFile file) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));

        Payment payment = new Payment();
        payment.setCart(cart);
        payment.setAmount(cart.getTotal());
        payment.setReference(reference);
        payment.setPaymentDate(new Date());
        payment.setStatus(PaymentStatus.EN_REVISION);
        payment.setReceiptPath(file.getOriginalFilename());
        payment.setPaymentMethod(cart.getPaymentMethod());

        paymentRepository.save(payment);

        fileService.saveFile(file, payment);
    }

    public void validatePayment(Integer paymentId, Boolean isValid, String username) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + paymentId));

        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        if (!user.getRole().getName().equals("ADMIN") && !user.getRole().getName().equals("PERSONAL")) {
            throw new RuntimeException("User does not have permission to validate payments.");
        }

        if (payment.getStatus() != PaymentStatus.EN_REVISION) {
            throw new RuntimeException("Payment is not in revision status.");
        }

        if (isValid) {
            payment.setStatus(PaymentStatus.VERIFICADO);
        } else {
            payment.setStatus(PaymentStatus.RECHAZADO);
        }

        payment.setValidatedBy(user);
        payment.setValidationDate(new Date());

        paymentRepository.save(payment);
    }

    // Tarea para notificar por correo los pagos confirmados o rechazados / limpiar carrito / restar stock
    @Transactional
    @Scheduled(fixedRate = 60000)
    public void processPendingPaymentEmails() {
        List<Payment> payments = paymentRepository
                .findByStatusInAndEmailSentFalse(List.of(PaymentStatus.VERIFICADO, PaymentStatus.RECHAZADO));

        // Bucle para verificar cada pago
        for (Payment payment : payments) {
            try {
                Cart cart = payment.getCart();
                Users user = cart.getUsers();
                String email = user.getEmail();
                String fullName = user.getFullname();
                String reference = payment.getReference();

                // Condición para verificar estado del pago
                if (payment.getStatus() == PaymentStatus.VERIFICADO) {
                    for (CartItems item : cart.getCartItems()) {
                        Products product = item.getProduct();
                        int quantity = item.getQuantity();

                        if (product.getStock() < quantity) {
                            throw new RuntimeException("Not enough stock for the product: " + product.getProductName());
                        }

                        product.setStock(product.getStock() - quantity);
                        productRepository.save(product);
                    }

                    cart.getCartItems().clear();
                    cartRepository.save(cart);

                    emailService.sendPaymentApprovedEmail(email, fullName, reference, payment);

                } else if (payment.getStatus() == PaymentStatus.RECHAZADO) {
                    emailService.sendPaymentRejectedEmail(email, fullName, reference);
                }

                payment.setEmailSent(true);
                paymentRepository.save(payment);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

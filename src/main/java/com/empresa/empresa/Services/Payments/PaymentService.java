package com.empresa.empresa.Services.Payments;

import com.empresa.empresa.Dto.Cart.CartItemsDto;
import com.empresa.empresa.Dto.Payments.PaymentDto;
import com.empresa.empresa.Dto.Product.ProductsDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
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

    // Get all payments
    public Page<PaymentDto> getPaymentsFiltered(String search, PaymentStatus status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        if ((search == null || search.trim().isEmpty()) && status == null) {
            return paymentRepository.findAll(pageable).map(this::mapToDto);
        }

        if (status == null) {
            return paymentRepository.findByReferenceOrUsername(search.trim(), pageable)
                    .map(this::mapToDto);
        }

        return paymentRepository.findByReferenceOrUsernameAndStatus(search.trim(), status, pageable)
                .map(this::mapToDto);
    }



    // Get payments by ID
    public PaymentDto getPaymentById(Integer paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + paymentId));
        return mapToDto(payment);
    }


    // Map Payment to DTO
    public PaymentDto mapToDto(Payment payment) {
        PaymentDto dto = new PaymentDto();
        dto.setIdPayment(payment.getId());
        dto.setFullName(payment.getCart().getUsers().getFullname());
        dto.setEmail(payment.getCart().getUsers().getEmail());
        dto.setDni(payment.getCart().getUsers().getDni());
        dto.setUsername(payment.getCart().getUsers().getUsername());
        dto.setIgv(payment.getCart().getIgv());
        dto.setCartItems(payment.getCart().getCartItems().stream().map(this::mapToCartItemsDto).toList());
        dto.setPaymentMethod(payment.getCart().getPaymentMethod().getName());
        dto.setTypeShipment(payment.getCart().getTypeShipment().name());
        dto.setTotalAmount(payment.getCart().getTotal());
        dto.setReference(payment.getReference());
        dto.setDatePayment(payment.getPaymentDate());
        dto.setVoucherFile(payment.getReceiptPath());
        dto.setAmount(payment.getAmount());
        dto.setStatus(payment.getStatus().name());
        return dto;
    }

    //Map to Cart Items Dto
    public CartItemsDto mapToCartItemsDto(CartItems cartItems) {
        CartItemsDto dto = new CartItemsDto();
        dto.setIdCartItem(cartItems.getId());
        dto.setProduct(mapProductsToDto(cartItems.getProduct()));
        dto.setQuantity(cartItems.getQuantity());
        dto.setSubTotal(cartItems.getSubTotal());
        return dto;
    }

    // Map Products to DTO
    public ProductsDto mapProductsToDto (Products products) {
        ProductsDto dto = new ProductsDto();
        dto.setIdProduct(products.getId());
        dto.setProductName(products.getProductName());
        dto.setProductDescription(products.getProductDescription());
        dto.setProductSku(products.getSku());
        dto.setProductPrice(products.getPrice());
        dto.setPriceCreditCard(products.getPrice_creditcard());
        dto.setQuantity(products.getQuantity());
        dto.setStock(products.getStock());
        dto.setProductOfferDiscount(products.getOfferDescount());
        dto.setPriceOffer(products.getPriceOffer());
        dto.setIdBrand(products.getBrand().getIdBrand());
        dto.setBrandName(products.getBrand().getBrandName());
        dto.setIdCategory(products.getCategory().getIdCategory());
        dto.setCategoryName(products.getCategory().getCategoryName());
        dto.setPdfFile(products.getPdfFile());
        dto.setIsOffer(products.getIsOffer());
        dto.setIsActive(products.getIsActive());
        dto.setProductImg(products.getProduct_image());
        return dto;
    }

    public void registerPayment(Integer cartId, String reference, MultipartFile file) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));

        Payment payment = new Payment();
        payment.setCart(cart);
        payment.setAmount(cart.getTotal());
        payment.setReference(reference);
        payment.setPaymentDate(LocalDateTime.now());
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
        payment.setValidationDate(LocalDateTime.now());

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
                    emailService.sendPaymentApprovedEmail(email, fullName, reference, payment);
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

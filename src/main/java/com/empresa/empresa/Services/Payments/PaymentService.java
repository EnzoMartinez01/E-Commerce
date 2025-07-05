package com.empresa.empresa.Services.Payments;

import com.empresa.empresa.Models.Cart.Cart;
import com.empresa.empresa.Models.Payments.Payment;
import com.empresa.empresa.Models.Payments.PaymentStatus;
import com.empresa.empresa.Repositories.Cart.CartRepository;
import com.empresa.empresa.Repositories.Payments.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final CartRepository cartRepository;
    private final UploadedPaymentFileService fileService;

    public PaymentService(PaymentRepository paymentRepository,
                          CartRepository cartRepository,
                          UploadedPaymentFileService fileService) {
        this.paymentRepository = paymentRepository;
        this.cartRepository = cartRepository;
        this.fileService = fileService;
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
}

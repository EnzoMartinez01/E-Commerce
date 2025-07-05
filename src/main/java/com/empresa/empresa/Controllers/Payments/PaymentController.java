package com.empresa.empresa.Controllers.Payments;

import com.empresa.empresa.Services.Payments.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/registerPayment")
    public ResponseEntity<String> registerPayment(
            @RequestParam Integer cartId,
            @RequestParam("reference") String reference,
            @RequestParam("file") MultipartFile file) {
        paymentService.registerPayment(cartId, reference, file);
        return ResponseEntity.status(HttpStatus.CREATED).body("Payment registered successfully.");
    }
}

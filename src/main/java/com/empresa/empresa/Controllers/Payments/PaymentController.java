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
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/registerPayment")
    public ResponseEntity<Map<String, String>> registerPayment(
            @RequestParam Integer cartId,
            @RequestParam("reference") String reference,
            @RequestParam("file") MultipartFile file) {

        paymentService.registerPayment(cartId, reference, file);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Payment registered, waiting for approbation.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/validatePayment")
    public ResponseEntity<Map<String, String>> validatePayment(
            @RequestParam Integer paymentId,
            @RequestParam Boolean isValid,
            Principal principal
    ) {
        String username = principal.getName();
        paymentService.validatePayment(paymentId, isValid, username);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Payment validation processed successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

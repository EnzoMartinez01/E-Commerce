package com.empresa.empresa.Controllers.Payments;

import com.empresa.empresa.Dto.Files.FileDownloadDto;
import com.empresa.empresa.Dto.Payments.PaymentDto;
import com.empresa.empresa.Models.Payments.UploadedPaymentFile;
import com.empresa.empresa.Services.Payments.PaymentService;
import com.empresa.empresa.Services.Payments.UploadedPaymentFileService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;
    private final UploadedPaymentFileService fileService;

    public PaymentController(PaymentService paymentService,
                             UploadedPaymentFileService fileService) {
        this.paymentService = paymentService;
        this.fileService = fileService;
    }

    @GetMapping("/getAllPayments")
    public ResponseEntity<Page<PaymentDto>> getAllPayments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<PaymentDto> payments = paymentService.getAllPayments(page, size);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/getPayment/{paymentId}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable Integer paymentId) {
        PaymentDto payment = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(payment);
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

    @GetMapping("/downloadVoucher/{paymentId}")
    public ResponseEntity<byte[]> downloadVoucher(@PathVariable Integer paymentId) {
        try {
            FileDownloadDto fileDto = fileService.downloadFile(paymentId);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(fileDto.getMimeType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileDto.getFileName() + "\"")
                    .body(fileDto.getData());

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}

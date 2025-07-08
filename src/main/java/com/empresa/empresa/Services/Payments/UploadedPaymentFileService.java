package com.empresa.empresa.Services.Payments;

import com.empresa.empresa.Dto.Files.FileDownloadDto;
import com.empresa.empresa.Models.Payments.Payment;
import com.empresa.empresa.Models.Payments.UploadedPaymentFile;
import com.empresa.empresa.Repositories.Payments.UploadedPaymentFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadedPaymentFileService {
    private final UploadedPaymentFileRepository uploadedPaymentFileRepository;

    private static final String UPLOAD_DIR = "uploads/payments/";

    public UploadedPaymentFile saveFile(MultipartFile file, Payment payment) {
        try {
            String docNumber = payment.getCart().getUsers().getDni();

            Path dirPath = Paths.get(UPLOAD_DIR, docNumber);
            Files.createDirectories(dirPath);

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = dirPath.resolve(fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            UploadedPaymentFile uploadedFile = new UploadedPaymentFile();
            uploadedFile.setFileName(file.getOriginalFilename());
            uploadedFile.setFilePath(path.toString());
            uploadedFile.setUploadDate(LocalDateTime.now());
            uploadedFile.setPayment(payment);

            return uploadedPaymentFileRepository.save(uploadedFile);
        } catch (IOException e) {
            throw new RuntimeException("Error saving file: " + e.getMessage(), e);
        }
    }

    public FileDownloadDto downloadFile(Integer paymentId) throws IOException {
        UploadedPaymentFile file = uploadedPaymentFileRepository.findByPayment_Id(paymentId)
                .orElseThrow(() -> new RuntimeException("File not found with PaymentId: " + paymentId));

        Path path = Paths.get(file.getFilePath());
        byte[] fileData = Files.readAllBytes(path);
        String mimeType = Files.probeContentType(path);

        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        return new FileDownloadDto(file.getFileName(), mimeType, fileData);
    }

}

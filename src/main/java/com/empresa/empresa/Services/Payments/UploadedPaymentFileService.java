package com.empresa.empresa.Services.Payments;

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
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadedPaymentFileService {
    private final UploadedPaymentFileRepository uploadedPaymentFileRepository;

    private static final String UPLOAD_DIR = "uploads/payments/";

    public UploadedPaymentFile saveFile(MultipartFile file, Payment payment) {
        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR, fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            UploadedPaymentFile uploadedFile = new UploadedPaymentFile();
            uploadedFile.setFileName(file.getOriginalFilename());
            uploadedFile.setFilePath(path.toString());
            uploadedFile.setUploadDate(new Date());
            uploadedFile.setPayment(payment);

            return uploadedPaymentFileRepository.save(uploadedFile);
        } catch (IOException e) {
            throw new RuntimeException("Error saving file: " + e.getMessage(), e);
        }
    }

    public byte[] downloadFile(Integer fileId) throws IOException {
        UploadedPaymentFile file = uploadedPaymentFileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found with id: " + fileId));

        return Files.readAllBytes(Paths.get(file.getFilePath()));
    }
}

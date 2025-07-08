package com.empresa.empresa.Dto.Files;

public class FileDownloadDto {
    private String fileName;
    private String mimeType;
    private byte[] data;

    // Constructor
    public FileDownloadDto(String fileName, String mimeType, byte[] data) {
        this.fileName = fileName;
        this.mimeType = mimeType;
        this.data = data;
    }

    // Getters
    public String getFileName() {
        return fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public byte[] getData() {
        return data;
    }
}

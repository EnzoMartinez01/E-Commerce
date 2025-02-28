package com.empresa.empresa.Dto.Contact;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FAQDto {
    private Integer idFaq;
    private String questionFaq;
    private String answerFaq;
    private Boolean isActive;
    private Integer idUser;
    private String createdbyFaq;
    private LocalDateTime createdatFaq;
}

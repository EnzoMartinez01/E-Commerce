package com.empresa.empresa.Dto.Contact;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ContactDto {
    private Integer idContact;
    private String namesContact;
    private String lastnameContact;
    private String socialreasonContact;
    private String emailContact;
    private String dnirucContact;
    private String telephoneContact;
    private String messageContact;
    private Integer idUser;
    private String createbyContact;
    private LocalDateTime createdatContact;
    private Boolean isagreeContact;
    private Integer idStatus;
    private String statusContact;

}

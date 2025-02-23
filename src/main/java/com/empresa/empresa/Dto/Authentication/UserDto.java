package com.empresa.empresa.Dto.Authentication;

import com.empresa.empresa.Dto.Addresses.AddressDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDto {
    private Integer idUser;
    private String fullName;
    private String roleName;
    private String username;
    private String email;
    private String phoneNumber;
    private String dni;
    private String socialReason;
    private LocalDate birthDate;
    private Boolean isActive;
    private List<AddressDto> addresses;
}

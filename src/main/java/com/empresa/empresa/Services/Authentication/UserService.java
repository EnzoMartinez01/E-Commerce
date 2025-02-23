package com.empresa.empresa.Services.Authentication;

import com.empresa.empresa.Dto.Addresses.AddressDto;
import com.empresa.empresa.Dto.Authentication.UserDto;
import com.empresa.empresa.Models.Addresess.Address;
import com.empresa.empresa.Models.Authentication.Users;
import com.empresa.empresa.Repositories.Authentication.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public Page<UserDto> allUsers(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Users> users = usersRepository.findAll(pageable);

        return users.map(this::mapToDto);
    }

    // Mapping User to UserDto
    public UserDto mapToDto(Users users) {
        UserDto dto = new UserDto();
        dto.setIdUser(users.getId());
        dto.setUsername(users.getUsername());
        dto.setRoleName(users.getRole().getName());
        dto.setFullName(users.getFullname());
        dto.setPhoneNumber(users.getTelephone());
        dto.setDni(users.getDni());
        dto.setIsActive(users.getIsActive());
        dto.setSocialReason(users.getSocialReason());
        dto.setEmail(users.getEmail());
        dto.setBirthDate(users.getBirthDate());
        List<AddressDto> addressDtos = (users.getAddresses() != null) ?
                users.getAddresses().stream().map(this::mapToAddressDto).toList() : Collections.emptyList();

        dto.setAddresses(addressDtos);

        return dto;
    }


    // Map to Dto
    public AddressDto mapToAddressDto(Address address) {
        AddressDto dto = new AddressDto();
        dto.setIdAdress(address.getId());
        dto.setStreetName(address.getStreet_name());
        dto.setStreetNumber(address.getStreet_number());
        dto.setIdCountry(address.getCountry().getId());
        dto.setCountryName(address.getCountry().getName());
        dto.setIdState(address.getState().getId());
        dto.setStateName(address.getState().getName());
        dto.setIdProvince(address.getProvince().getId());
        dto.setProvinceName(address.getProvince().getName());
        dto.setIdDistricts(address.getDistrict().getId());
        dto.setDistrictsName(address.getDistrict().getName());
        return dto;
    }

    // Get user by username
    public UserDto getUserByUsername(String username) {
        return usersRepository.findByUsername(username)
                .map(this::mapToDto)
                .orElseThrow(() -> new UsernameNotFoundException("User not fount with username: " + username));
    }
}

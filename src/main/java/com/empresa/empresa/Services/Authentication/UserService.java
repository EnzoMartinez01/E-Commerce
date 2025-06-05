package com.empresa.empresa.Services.Authentication;

import com.empresa.empresa.Dto.Addresses.AddressDto;
import com.empresa.empresa.Dto.Authentication.UserDto;
import com.empresa.empresa.Models.Addresess.Address;
import com.empresa.empresa.Models.Authentication.Roles;
import com.empresa.empresa.Models.Authentication.Users;
import com.empresa.empresa.Repositories.Authentication.RolesRepository;
import com.empresa.empresa.Repositories.Authentication.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.empresa.empresa.Dto.Authentication.UserUpdateDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;

    public UserService(UsersRepository usersRepository,
                       RolesRepository rolesRepository) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
    }

    public Page<UserDto> allUsers(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Users> users = usersRepository.findAll(pageable);

        return users.map(this::mapToDto);
    }

    public Page<UserDto> getUsersByFilters(String searchTerms, Integer roleId, Boolean isActive, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Users> users = usersRepository.findByFilters(searchTerms, roleId, isActive, pageable);

        return users.map(this::mapToDto);
    }

    public UserDto getUserById(Integer idUser){
        Users user = usersRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + idUser));
        return mapToDto(user);
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

    // Updated User
    public Users updateUser(Integer idUser, Users updatedUser) {
        try {
            Users existingUser = usersRepository.findById(idUser)
                    .orElseThrow(() -> new RuntimeException("User not fount with Id: " + idUser));

            if (updatedUser.getNames() != null && !updatedUser.getNames().isEmpty()) {
                existingUser.setNames(updatedUser.getNames());
            }

            if (updatedUser.getLastnames() != null && !updatedUser.getLastnames().isEmpty()) {
                existingUser.setLastnames(updatedUser.getLastnames());
            }

            if (updatedUser.getDni() != null && !updatedUser.getDni().isEmpty()) {
                existingUser.setDni(updatedUser.getDni());
            }

            if (updatedUser.getSocialReason() != null && !updatedUser.getSocialReason().isEmpty()) {
                existingUser.setSocialReason(updatedUser.getSocialReason());
            }

            if (updatedUser.getTelephone() != null && !updatedUser.getTelephone().isEmpty()) {
                existingUser.setTelephone(updatedUser.getTelephone());
            }

            if (updatedUser.getEmail() != null && !updatedUser.getEmail().isEmpty()) {
                existingUser.setEmail(updatedUser.getEmail());
            }

            if (updatedUser.getBirthDate() != null) {
                existingUser.setBirthDate(updatedUser.getBirthDate());
            }

            if (updatedUser.getUsername() != null && !updatedUser.getUsername().isEmpty()) {
                existingUser.setUsername(updatedUser.getUsername());
            }

            if (updatedUser.getRole() != null) {
                existingUser.setRole(updatedUser.getRole());
            }

            if (updatedUser.getIsActive() != null) {
                existingUser.setIsActive(updatedUser.getIsActive());
            }

            if ((updatedUser.getNames() != null && !updatedUser.getNames().isEmpty())
                    || (updatedUser.getLastnames() != null && !updatedUser.getLastnames().isEmpty())) {
                existingUser.setFullname(
                        (updatedUser.getNames() != null ? updatedUser.getNames() : existingUser.getNames()) + " " +
                                (updatedUser.getLastnames() != null ? updatedUser.getLastnames() : existingUser.getLastnames())
                );
            }


            return usersRepository.save(existingUser);
        } catch (Exception e) {
            logger.error("Error al actualizar Usuario", e);
            throw new RuntimeException("Error al actualizar Usuario", e);
        }
    }

    public void deactivateUser(Integer idUser) {
        Users user = usersRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + idUser));

        if (!user.getIsActive()) {
            throw new IllegalStateException("User is already deactivated.");
        }

        user.setIsActive(false);
        usersRepository.save(user);
    }

    // Get Roles all
    public List<Roles> getRolesAll()
    {
        return rolesRepository.findAll();
    }

}

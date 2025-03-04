package com.empresa.empresa.Services.Addresses;

import com.empresa.empresa.Dto.Addresses.AddressDto;
import com.empresa.empresa.Models.Addresess.*;
import com.empresa.empresa.Models.Authentication.CustomUserDetails;
import com.empresa.empresa.Models.Authentication.Users;
import com.empresa.empresa.Repositories.Adresses.*;
import com.empresa.empresa.Repositories.Authentication.UsersRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    private final static Logger logger = LoggerFactory.getLogger(AddressService.class);

    private final AddressRepository addressRepository;
    private final UsersRepository usersRepository;
    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;
    private final ProvincesRepository provincesRepository;
    private final DistrictsRepository districtsRepository;

    public AddressService(AddressRepository addressRepository,
                          UsersRepository usersRepository,
                          CountryRepository countryRepository,
                          StateRepository stateRepository,
                          ProvincesRepository provincesRepository,
                          DistrictsRepository districtsRepository) {
        this.addressRepository = addressRepository;
        this.usersRepository = usersRepository;
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
        this.provincesRepository = provincesRepository;
        this.districtsRepository = districtsRepository;
    }

    // Get all addresses
    public Page<AddressDto> getAllAddresses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Address> addresses = addressRepository.findAll(pageable);
        return addresses.map(this::mapToDto);
    }

    // Get Addresses by User
    public Page<AddressDto> getAddressesByUser(int page, int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Users currentUser = customUserDetails.getUsers();

        Users users = usersRepository.findById(currentUser.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Pageable pageable = PageRequest.of(page, size);
        Page<Address> addresses = addressRepository.findByUsers(pageable, users);
        return addresses.map(this::mapToDto);
    }

    // Map to Dto
    public AddressDto mapToDto(Address address) {
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

    // Add Address
    @Transactional
    public List<Address> addAddresses(List<Address> addresses) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            Users currentUser = customUserDetails.getUsers();

            Users users = usersRepository.findById(currentUser.getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            for (Address address : addresses) {
                address.setUsers(users);
            }

            return addressRepository.saveAll(addresses);
        } catch (Exception e) {
            logger.error("Error al agregar direcciones", e);
            throw new RuntimeException("Error al agregar direcciones", e);
        }
    }

    // Update Address
    @Transactional
    public Address updateAddress(Integer id, Address updatedAddress) {
        try {
            Address existingAddress = addressRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Address not found"));

            if (updatedAddress.getStreet_name() != null && !updatedAddress.getStreet_name().isEmpty()) {
                existingAddress.setStreet_name(updatedAddress.getStreet_name());
            }

            if (updatedAddress.getStreet_number() != null && !updatedAddress.getStreet_number().isEmpty()) {
                existingAddress.setStreet_number(updatedAddress.getStreet_number());
            }

            if (updatedAddress.getCountry() != null) {
                existingAddress.setCountry(updatedAddress.getCountry());
            }

            if (updatedAddress.getState() != null) {
                existingAddress.setState(updatedAddress.getState());
            }

            if (updatedAddress.getProvince() != null) {
                existingAddress.setProvince(updatedAddress.getProvince());
            }

            if (updatedAddress.getDistrict() != null) {
                existingAddress.setDistrict(updatedAddress.getDistrict());
            }

            return addressRepository.save(existingAddress);
        } catch (Exception e) {
            logger.error("Error al actualizar dirección", e);
            throw new RuntimeException("Error al actualizar dirección", e);
        }
    }

    // Get all Countries
    public List<Country> getAllCountries() {
        try {
            return countryRepository.findAll();
        } catch (Exception e) {
            logger.error("Error al obtener países", e);
            throw new RuntimeException("Error al obtener países", e);
        }
    }

    // Get all States
    public List<State> getAllStates(Integer countryId) {
        try {
            Country country = countryRepository.findById(countryId)
                    .orElseThrow(() -> new RuntimeException("Country not found with ID: " + countryId));
            return stateRepository.findByCountry(country);
        } catch (Exception e) {
            logger.error("Error al obtener estados", e);
            throw new RuntimeException("Error al obtener estados", e);
        }
    }

    // Get all Provinces
    public List<Province> getAllProvinces(Integer stateId) {
        try {
            State state = stateRepository.findById(stateId)
                    .orElseThrow(() -> new RuntimeException("State not found with ID: " + stateId));
            return provincesRepository.findByState(state);
        } catch (Exception e) {
            logger.error("Error al obtener provincias", e);
            throw new RuntimeException("Error al obtener provincias", e);
        }
    }

    // Get all Districts
    public List<Districts> getAllDistricts(Integer provinceId) {
        try {
            Province province = provincesRepository.findById(provinceId)
                    .orElseThrow(() -> new RuntimeException("Province not found with ID: " + provinceId));
            return districtsRepository.findByProvince(province);
        } catch (Exception e) {
            logger.error("Error al obtener distritos", e);
            throw new RuntimeException("Error al obtener distritos", e);
        }
    }
}

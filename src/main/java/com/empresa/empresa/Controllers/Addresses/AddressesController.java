package com.empresa.empresa.Controllers.Addresses;

import com.empresa.empresa.Dto.Addresses.AddressDto;
import com.empresa.empresa.Models.Addresess.*;
import com.empresa.empresa.Services.Addresses.AddressService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressesController {
    private final AddressService addressService;

    public AddressesController(AddressService addressService) {
        this.addressService = addressService;
    }

    // Get all addresses
    @GetMapping("/getAllAddresses")
    public Page<AddressDto> getAllAddresses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return addressService.getAllAddresses(page, size);
    }

    // Get Addresses by User
    @GetMapping("/getByUser")
    public Page<AddressDto> getByUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return addressService.getAddressesByUser(page, size);
    }

    // Add Addresses
    @PostMapping("/addAddress")
    public ResponseEntity<Map<String, String>> addAddress(
            @RequestBody List<Address> address) {
        List<Address> savedAddress = addressService.addAddresses(address);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Address created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Updated Address
    @PutMapping("/updatedAddress/{idAddress}")
    public ResponseEntity<Address> updateAddress(
            @PathVariable Integer idAddress,
            @RequestBody Address updatedAddress) {
        Address address = addressService.updateAddress(idAddress, updatedAddress);
        return ResponseEntity.ok(address);
    }

    // Delete Address
    @DeleteMapping("/deleteAddress/{idAddress}")
    public ResponseEntity<Map<String, String>> deleteAddress(@PathVariable Integer idAddress) {
        addressService.deleteAddress(idAddress);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Address deleted successfully");
        return ResponseEntity.ok(response);
    }

    // Get all Countries
    @GetMapping("/getAllCountries")
    public List<Country> getAllCountries() {
        return addressService.getAllCountries();
    }

    // Get all States
    @GetMapping("/getAllStates/{countryId}")
    public List<State> getAllStates(@PathVariable Integer countryId) {
        return addressService.getAllStates(countryId);
    }

    // Get all Provinces
    @GetMapping("/getAllProvinces/{stateId}")
    public List<Province> getAllProvinces(@PathVariable Integer stateId) {
        return addressService.getAllProvinces(stateId);
    }

    // Get all Districts
    @GetMapping("/getAllDistricts/{provinceId}")
    public List<Districts> getAllDistricts(@PathVariable Integer provinceId) {
        return addressService.getAllDistricts(provinceId);
    }
}

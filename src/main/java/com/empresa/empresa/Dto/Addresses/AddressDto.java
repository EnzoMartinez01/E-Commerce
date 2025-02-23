package com.empresa.empresa.Dto.Addresses;

import lombok.Data;

@Data
public class AddressDto {
    private Integer idAdress;
    private String streetName;
    private String streetNumber;
    private Integer idCountry;
    private String countryName;
    private Integer idState;
    private String stateName;
    private Integer idProvince;
    private String provinceName;
    private Integer idDistricts;
    private String districtsName;



}

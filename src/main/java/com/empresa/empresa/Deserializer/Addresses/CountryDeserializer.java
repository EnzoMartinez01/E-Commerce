package com.empresa.empresa.Deserializer.Addresses;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.empresa.empresa.Models.Addresess.Country;
import com.empresa.empresa.Repositories.Adresses.CountryRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

@Component
public class CountryDeserializer extends JsonDeserializer<Country> {
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException {
        Integer id = p.getIntValue();
        return countryRepository.findById(id).orElseThrow(() -> new RuntimeException("Country not found"));
    }
}

package com.empresa.empresa.Deserializer.Addresses;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.empresa.empresa.Models.Addresess.Province;
import com.empresa.empresa.Repositories.Adresses.ProvincesRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

@Component
public class ProvinceDeserializer extends JsonDeserializer<Province> {
    @Autowired
    private ProvincesRepository provinceRepository;

    @Override
    public Province deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException {
        Integer id = p.getIntValue();
        return provinceRepository.findById(id).orElseThrow(() -> new RuntimeException("Province not found"));
    }
}

package com.empresa.empresa.Deserializer.Addresses;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.empresa.empresa.Models.Addresess.Districts;
import com.empresa.empresa.Repositories.Adresses.DistrictsRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DistrictsDeserializer extends JsonDeserializer <Districts> {
    @Autowired
    private DistrictsRepository districtsRepository;

    @Override
    public Districts deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException{
        Integer id = p.getIntValue();
        return districtsRepository.findById(id).orElseThrow(() -> new RuntimeException("Districts not Found"));
    }
    
}
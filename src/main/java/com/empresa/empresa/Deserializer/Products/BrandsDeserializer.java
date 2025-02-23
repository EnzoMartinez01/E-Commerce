package com.empresa.empresa.Deserializer.Products;

import com.empresa.empresa.Models.Products.Brands;
import com.empresa.empresa.Repositories.Products.BrandsRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BrandsDeserializer extends JsonDeserializer<Brands> {
    @Autowired
    private BrandsRepository brandsRepository;

    @Override
    public Brands deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException {
        Integer id = p.getIntValue();
        return brandsRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid Brand id: " + id));
    }
}

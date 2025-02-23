package com.empresa.empresa.Deserializer.Products;

import com.empresa.empresa.Models.Products.Products;
import com.empresa.empresa.Repositories.Products.ProductsRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProductsDeserializer extends JsonDeserializer<Products> {
    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public Products deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException {
        Integer id = p.getIntValue();
        return productsRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid Products id: " + id));
    }
}

package com.empresa.empresa.Deserializer.Products;

import com.empresa.empresa.Models.Products.Categories;
import com.empresa.empresa.Repositories.Products.CategoriesRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CategoriesDeserializer extends JsonDeserializer<Categories> {
    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public Categories deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException {
        Integer id = p.getIntValue();
        return categoriesRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid Category id: " + id));
    }
}

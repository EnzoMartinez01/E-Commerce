package com.empresa.empresa.Deserializer.Products;

import com.empresa.empresa.Models.Products.SubCategories;
import com.empresa.empresa.Repositories.Products.SubCategoryRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SubCategoryDeserializer extends JsonDeserializer<SubCategories> {
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Override
    public SubCategories deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException {
        Integer id = p.getIntValue();
        return subCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid SubCategory id: " + id));
    }
}

package com.empresa.empresa.Deserializer.Cart;

import com.empresa.empresa.Models.Cart.Cart;
import com.empresa.empresa.Repositories.Cart.CartRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CartDeserializer extends JsonDeserializer<Cart> {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException {
        Integer id = p.getIntValue();
        return cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid Cart id: " + id));
    }
}

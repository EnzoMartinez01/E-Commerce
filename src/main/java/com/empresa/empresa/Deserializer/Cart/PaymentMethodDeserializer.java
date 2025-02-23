package com.empresa.empresa.Deserializer.Cart;

import com.empresa.empresa.Models.Cart.PaymentMethod;
import com.empresa.empresa.Repositories.Cart.PaymentMethodRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PaymentMethodDeserializer extends JsonDeserializer<PaymentMethod> {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Override
    public PaymentMethod deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException {
        Integer id = p.getIntValue();
        return paymentMethodRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid Payment Method id: " + id));
    }
}

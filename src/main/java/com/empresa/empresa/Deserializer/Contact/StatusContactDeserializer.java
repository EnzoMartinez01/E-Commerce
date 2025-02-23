package com.empresa.empresa.Deserializer.Contact;

import com.empresa.empresa.Models.Contact.StatusContact;
import com.empresa.empresa.Repositories.Contact.StatusContactRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StatusContactDeserializer extends JsonDeserializer<StatusContact> {
    @Autowired
    private StatusContactRepository statusContactRepository;

    @Override
    public StatusContact deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException {
        Integer id = p.getIntValue();
        return statusContactRepository.findById(id).orElseThrow(() -> new RuntimeException("Status Contact not found"));
    }
}

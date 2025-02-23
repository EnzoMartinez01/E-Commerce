package com.empresa.empresa.Deserializer.Addresses;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.empresa.empresa.Models.Addresess.State;
import com.empresa.empresa.Repositories.Adresses.StateRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

@Component
public class StateDeserializer extends JsonDeserializer<State> {
    @Autowired
    private StateRepository stateDeserializer;

    @Override
    public State deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException {
    Integer id = p.getIntValue();
    return stateDeserializer.findById(id).orElseThrow(() -> new RuntimeException("State not found"));
    } 
}
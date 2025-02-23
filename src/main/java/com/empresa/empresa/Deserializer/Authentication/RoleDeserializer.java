package com.empresa.empresa.Deserializer.Authentication;

import com.empresa.empresa.Models.Authentication.Roles;
import com.empresa.empresa.Repositories.Authentication.RolesRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RoleDeserializer extends JsonDeserializer<Roles> {
    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public Roles deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException {
        Integer id = p.getIntValue();
        return rolesRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
    }
}

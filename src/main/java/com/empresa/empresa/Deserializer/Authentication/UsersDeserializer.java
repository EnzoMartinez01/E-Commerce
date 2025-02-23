package com.empresa.empresa.Deserializer.Authentication;

import com.empresa.empresa.Models.Authentication.Users;
import com.empresa.empresa.Repositories.Authentication.UsersRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UsersDeserializer extends JsonDeserializer<Users> {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException {
        Integer id = p.getIntValue();
        return usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}

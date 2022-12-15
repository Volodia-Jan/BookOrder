package com.application.bookorder.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {

    public static String serialize(final Object object){
        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(object);
        } catch (final JsonProcessingException e){
            e.printStackTrace();
            return null;
        }
    }
}
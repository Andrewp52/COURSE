package com.geekbrains.geekmarketwinter.utils;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonConverter {
    ObjectMapper mapper = new ObjectMapper();

    {
        mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public String objectToString(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }

    public Object bytesToObject(byte[] bytes, Class type) throws IOException {
        return mapper.readValue(bytes, type);
    }
}

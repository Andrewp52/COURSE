package ru.geekbrain.domain.headers;

import java.util.Arrays;

public enum HttpMethod {
    GET,
    POST,
    PUT;

    public static HttpMethod findByName(String name){
        return Arrays.stream(HttpMethod.values())
                .filter(httpMethod -> name.contains(httpMethod.name()))
                .findFirst().orElseGet(() -> {return null;});
    }
}

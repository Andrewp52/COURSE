package ru.geekbrain.domain.headers;

import java.util.Arrays;

public enum HttpHeader {
    COOKIE("Cookie"),
    REFERER("Referer"),
    CONTENT_TYPE("Content-Type");

    private String prefix;

    HttpHeader(String prefix) {
        this.prefix = prefix;
    }

    public static HttpHeader findByPrefix(String prefix){
        return Arrays.stream(HttpHeader.values())
                .filter(httpHeader -> prefix.equals(httpHeader.prefix))
                .findFirst()
                .orElseGet(() -> {return null;});
    }

    @Override
    public String toString() {
        return prefix;
    }
}

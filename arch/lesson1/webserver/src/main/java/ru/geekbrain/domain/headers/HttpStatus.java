package ru.geekbrain.domain.headers;

import java.util.StringJoiner;

public enum HttpStatus {
    OK(200, "OK"),
    NOT_FOUND(404, "Not found"),
    ERROR(500, "Internal Server Error");

    private static final String prefix = "HTTP/1.1 ";
    private Integer code;
    private String message;

    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return new StringJoiner(" ", prefix, "")
                .add(code.toString())
                .add(message)
                .toString();
    }
}

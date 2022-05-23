package ru.geekbrains.homework8.exceptions;

import java.time.LocalDateTime;

public class ExceptionDTO {
    private String name;
    private String message;
    private LocalDateTime timestamp;

    public ExceptionDTO(String name, String message) {
        this.name = name;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

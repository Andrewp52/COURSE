package ru.geekbrain.domain.headers;

public enum ContentType{
    TEXT_HTML("text/html; charset=utf-8");

    private String type;

    ContentType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}

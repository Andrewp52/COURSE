package ru.geekbrain.headers;

public enum ContentType implements Header{
    TEXT_HTML("text/html; charset=utf-8");

    private String prefix = "Content-Type: ";
    private String type;

    ContentType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return prefix + type;
    }
}

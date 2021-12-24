package ru.geekbrain.services;

import ru.geekbrain.domain.Request;

import java.io.Closeable;

public interface Service extends Closeable {
    void serveRequest(Request request);

}

package ru.geekbrain.IO.writers;

import ru.geekbrain.domain.Response;

public interface ResponseWriter {
    void writeResponse(Response response);
}

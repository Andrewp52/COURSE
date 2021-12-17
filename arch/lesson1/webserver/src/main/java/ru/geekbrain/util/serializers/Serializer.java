package ru.geekbrain.util.serializers;

import ru.geekbrain.domain.Response;

public interface Serializer {
    String serialize(Response response);
}

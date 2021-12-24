package ru.geekbrain.util.serializers;

import ru.geekbrain.domain.Response;

import java.util.StringJoiner;

class ResponseSerializer implements Serializer{

    @Override
    public String serialize(Response response) {
        StringJoiner sj = new StringJoiner(System.lineSeparator(), "", System.lineSeparator());
        sj.add(response.getStatus().toString());
        response.getHeaders().forEach((header, str) -> sj.add(header.toString() + ": " + str));
        sj.add(System.lineSeparator());
        sj.add(response.getBody());
        return sj.toString();
    }
}

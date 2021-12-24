package ru.geekbrains.service;

import ru.geekbrains.domain.HttpRequest;

import java.util.Deque;

class RequestParserImpl implements RequestParser {

    public HttpRequest parseRequest(Deque<String> rawRequest) {
        HttpRequest.Builder builder = HttpRequest.createBuilder();
        String[] firstLine = rawRequest.pollFirst().split(" ");
        builder.withMethod(firstLine[0]);
        builder.withUrl(firstLine[1]);

        while (!rawRequest.isEmpty()) {
            String line = rawRequest.pollFirst();
            if (line.isBlank()) {
                break;
            }
            String[] header = line.split(": ");
            builder.withHeader(header[0], header[1]);
        }
        StringBuilder body = new StringBuilder();
        while (!rawRequest.isEmpty()) {
            body.append(rawRequest.pollFirst());
        }
        builder.withBody(body.toString());
        return builder.build();
    }
}

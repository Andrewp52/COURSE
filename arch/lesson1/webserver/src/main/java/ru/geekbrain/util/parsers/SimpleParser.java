package ru.geekbrain.util.parsers;

import ru.geekbrain.domain.Request;
import ru.geekbrain.domain.headers.HttpHeader;
import ru.geekbrain.domain.headers.HttpMethod;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

class SimpleParser implements Parser {
    @Override
    public Request parse(Deque<String> raw){
        String[] firstLine = raw.pollFirst().split(" ");
        HttpMethod method = HttpMethod.findByName(firstLine[0]);
        String url = firstLine[1];

        Map<HttpHeader, String> headers = new HashMap<>();
        while (!raw.isEmpty()) {
            String line = raw.pollFirst();
            if (line.isBlank()) {
                break;
            }
            String[] rawHeader = line.split(": ");
            HttpHeader header = HttpHeader.findByPrefix(rawHeader[0]);
            if(header != null){
                headers.put(header, rawHeader[1]);
            }
        }

        StringBuilder body = new StringBuilder();
        while (!raw.isEmpty()) {
            body.append(raw.pollFirst());
        }

        return Request.Builder.getBuilder()
                .withMethod(method)
                .withUrl(url)
                .withHeaders(headers)
                .withBody(body.toString())
                .build();
    }
}

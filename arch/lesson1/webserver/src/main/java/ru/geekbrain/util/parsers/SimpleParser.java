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
        Request.Builder builder = Request.Builder.getBuilder();
        String[] firstLine = raw.pollFirst().split(" ");

        builder.withMethod(HttpMethod.findByName(firstLine[0]));
        builder.withUrl(firstLine[1]);

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
        builder.withHeaders(headers);

        StringBuilder body = new StringBuilder();
        while (!raw.isEmpty()) {
            body.append(raw.pollFirst());
        }
        builder.withBody(body.toString());
        return builder.build();
    }
}

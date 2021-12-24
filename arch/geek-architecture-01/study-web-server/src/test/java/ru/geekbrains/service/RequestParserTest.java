package ru.geekbrains.service;

import org.junit.Assert;
import org.junit.Test;
import ru.geekbrains.domain.HttpRequest;

import java.util.Deque;
import java.util.LinkedList;

public class RequestParserTest {

    private final RequestParserImpl parser = new RequestParserImpl();

    @Test
    public void testSimpleRequest() {
        Deque<String> request = new LinkedList<>();
        request.add("GET /some/file.html HTTP/1.1");
        request.add("Some-header: value");
        request.add("\n");
        HttpRequest httpRequest = parser.parseRequest(request);

        Assert.assertEquals("GET", httpRequest.getMethod());
        Assert.assertEquals("/some/file.html", httpRequest.getUrl());
    }
}

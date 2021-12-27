package ru.geekbrains.handler;

import ru.geekbrains.config.Config;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.domain.ResponseCode;
import ru.geekbrains.service.ResponseSerializer;
import ru.geekbrains.service.SocketService;

@Handler(order = 2, method = "PUT")
public class PutMethodHandler extends MethodHandler {

    public PutMethodHandler(String method, MethodHandler next, SocketService socketService, ResponseSerializer responseSerializer, Config config) {
        super(method, next, socketService, responseSerializer, config);
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        return HttpResponse.createBuilder()
                .withStatus(ResponseCode.OK)
                .withHeader("Content-Type", "text/html; charset=utf-8")
                .withBody("<h1>PUT method handled</h1>")
                .build();
    }
}

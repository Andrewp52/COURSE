package ru.geekbrains.handler;

import ru.geekbrains.config.Config;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.domain.ResponseCode;
import ru.geekbrains.service.ResponseSerializer;
import ru.geekbrains.service.SocketService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Handler(order = 0)
public class GetMethodHandler extends MethodHandler {

    public GetMethodHandler(String method, MethodHandler next, SocketService socketService, ResponseSerializer responseSerializer, Config config) {
        super(method, next, socketService, responseSerializer, config);
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        Path path = Paths.get(config.getWwwHome(), request.getUrl());

        if (!Files.exists(path)) {
            return HttpResponse.createBuilder()
                    .withStatus(ResponseCode.NOT_FOUND)
                    .withHeader("Content-Type", "text/html; charset=utf-8")
                    .withBody("<h1>Файл не найден!</h1>")
                    .build();
        }

        StringBuilder sb = new StringBuilder();
        try {
            Files.readAllLines(path).forEach(sb::append);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return HttpResponse.createBuilder()
                .withStatus(ResponseCode.OK)
                .withHeader("Content-Type", "text/html; charset=utf-8")
                .withBody(sb.toString())
                .build();
    }
}

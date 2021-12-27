package ru.geekbrains.handler;

import ru.geekbrains.config.Config;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.service.ResponseSerializer;
import ru.geekbrains.service.SocketService;

import java.util.function.Function;

public class StrategyMethodHandler extends MethodHandler {

    private final Function<HttpRequest, HttpResponse> strategy;

    public StrategyMethodHandler(String method, MethodHandler next,
                                 SocketService socketService, ResponseSerializer responseSerializer, Config config,
                                 Function<HttpRequest, HttpResponse> strategy) {
        super(method, next, socketService, responseSerializer, config);
        this.strategy = strategy;
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        return strategy.apply(request);
    }
}

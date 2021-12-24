package ru.geekbrains.handler.factory;

import ru.geekbrains.config.Config;
import ru.geekbrains.handler.MethodHandler;
import ru.geekbrains.service.ResponseSerializer;
import ru.geekbrains.service.SocketService;

public abstract class MethodHandlerFactory {
    protected final SocketService service;
    protected final ResponseSerializer serializer;
    protected final Config config;

    public MethodHandlerFactory(SocketService socketService, ResponseSerializer responseSerializer, Config config) {
        this.service = socketService;
        this.serializer = responseSerializer;
        this.config = config;
    }

    public abstract MethodHandler create();

}

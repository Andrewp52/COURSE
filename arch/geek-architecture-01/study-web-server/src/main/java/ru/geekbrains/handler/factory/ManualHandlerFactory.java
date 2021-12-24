package ru.geekbrains.handler.factory;

import ru.geekbrains.config.Config;
import ru.geekbrains.handler.GetMethodHandler;
import ru.geekbrains.handler.MethodHandler;
import ru.geekbrains.handler.PostMethodHandler;
import ru.geekbrains.handler.PutMethodHandler;
import ru.geekbrains.service.ResponseSerializer;
import ru.geekbrains.service.SocketService;

public class ManualHandlerFactory extends MethodHandlerFactory{
    public ManualHandlerFactory(SocketService socketService, ResponseSerializer responseSerializer, Config config) {
        super(socketService, responseSerializer, config);
    }

    @Override
    public MethodHandler create() {
        PutMethodHandler putHandler = new PutMethodHandler("PUT", null, super.service, super.serializer, super.config);
        PostMethodHandler postHandler = new PostMethodHandler("POST", putHandler, super.service, super.serializer, super.config);
        return new GetMethodHandler("GET", postHandler, super.service, super.serializer, super.config);
    }
}

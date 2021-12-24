package ru.geekbrains.handler;

import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.service.RequestParser;
import ru.geekbrains.service.SocketService;

import java.io.IOException;
import java.util.Deque;

public class RequestHandler implements Runnable {

    private final SocketService socketService;
    private final RequestParser requestParser;
    private final MethodHandler methodHandler;

    public RequestHandler(SocketService socketService,
                          RequestParser requestParser,
                          MethodHandler methodHandler) {
        this.socketService = socketService;
        this.requestParser = requestParser;
        this.methodHandler = methodHandler;
    }

    @Override
    public void run() {
        Deque<String> rawRequest = socketService.readRequest();
        HttpRequest httpRequest = requestParser.parseRequest(rawRequest);

        methodHandler.handle(httpRequest);
        try {
            socketService.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        System.out.println("Client disconnected!");
    }
}

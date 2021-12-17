package ru.geekbrain;

import ru.geekbrain.IO.readers.RequestReader;
import ru.geekbrain.util.parsers.Parser;
import ru.geekbrain.domain.Request;
import ru.geekbrain.services.Service;

import java.io.IOException;

/**
 * Handling client connection.
 * Calling by server, when client is connected.
 */
public class ClientHandler implements Runnable{
    private final RequestReader requestReader;
    private final Parser parser;
    private final Service service;

    public ClientHandler(Service service, RequestReader requestReader, Parser requestParser) {
        this.requestReader = requestReader;
        this.parser = requestParser;
        this.service = service;
    }

    @Override
    public void run() {
        Request request = parser.parse(requestReader.readRequest());
        try (service){
            service.serveRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Client disconnected!");
    }
}

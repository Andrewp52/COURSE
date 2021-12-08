package ru.geekbrain;

import ru.geekbrain.IO.RequestReader;
import ru.geekbrain.IO.PrintResponseWriter;
import ru.geekbrain.headers.ContentType;

import java.io.IOException;
import java.net.Socket;
import java.nio.file.Path;

/**
 * Handling client connection.
 * Calling by server, when client is connected.
 */
public class ClientHandler implements Runnable{
    private final String home;
    private final Socket socket;
    private RequestReader requestReader;
    private PrintResponseWriter responseWriter;

    public ClientHandler(Socket socket, String home) {
        this.home = home;
        this.socket = socket;
        this.responseWriter = new PrintResponseWriter(socket);
        this.requestReader = new RequestReader(socket);
    }

    @Override
    public void run() {
        String request = requestReader.readRequest();
        Path path = Path.of(home, request);
        responseWriter.writeResponse(path, ContentType.TEXT_HTML);

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Client disconnected!");

    }
}

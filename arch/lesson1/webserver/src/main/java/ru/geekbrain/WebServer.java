package ru.geekbrain;

import ru.geekbrain.IO.FileWorker;
import ru.geekbrain.util.parsers.ParserFactory;
import ru.geekbrain.IO.readers.RequestReaderImpl;
import ru.geekbrain.services.SocketService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(Config.port)) {
            System.out.println("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");

                new ClientHandler(
                        new SocketService(socket, new FileWorker(Config.WWW)),
                        new RequestReaderImpl(socket),
                        ParserFactory.getParser()
                ).run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

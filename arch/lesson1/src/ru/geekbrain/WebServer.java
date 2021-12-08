package ru.geekbrain;

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
                new ClientHandler(socket, Config.WWW).run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

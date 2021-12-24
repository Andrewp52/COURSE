package ru.geekbrains;

import ru.geekbrains.config.Config;
import ru.geekbrains.config.ConfigFactory;
import ru.geekbrains.handler.MethodHandler;
import ru.geekbrains.handler.RequestHandler;
import ru.geekbrains.handler.factory.MethodHandlerFactory;
import ru.geekbrains.handler.factory.reflect.ReflectHandlerFactory;
import ru.geekbrains.service.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    public static void main(String[] args) {
        Config config = ConfigFactory.create(args);
        try (ServerSocket serverSocket = new ServerSocket(config.getPort())) {
            System.out.printf("Server started at port %d!%n", config.getPort());

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");

                SocketService socketService = SocketServiceFactory.createSocketService(socket);
                ResponseSerializer responseSerializer = ResponseSerializerFactory.createResponseSerializer();
                MethodHandlerFactory handlerFactory = new ReflectHandlerFactory(
                        MethodHandler.class.getPackage(),
                        socketService,
                        responseSerializer,
                        config
                );
//                MethodHandlerFactory handlerFactory = new ManualHandlerFactory(socketService, responseSerializer, config);
                new Thread(new RequestHandler(
                        socketService,
                        RequestParserFactory.createRequestParser(),
                        handlerFactory.create())
                ).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

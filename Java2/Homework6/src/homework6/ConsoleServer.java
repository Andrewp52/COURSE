package homework6;

import java.io.*;
import java.net.ServerSocket;

public class ConsoleServer {
    final static int PORT = 8888;

    public static void main(String[] args) {
        try {
            Exchanger exchanger = new Exchanger(new ServerSocket(PORT).accept());
            exchanger.start();
            exchanger.startMessaging();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server stopped");
    }
}

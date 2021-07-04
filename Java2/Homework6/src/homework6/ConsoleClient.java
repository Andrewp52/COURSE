package homework6;

import java.io.*;
import java.net.Socket;

public class ConsoleClient {
    static final String ADDRESS = "localhost";
    static final int PORT = 8888;

    public static void main(String[] args) {
        try {
            Exchanger exchanger = new Exchanger(new Socket(ADDRESS, PORT));
            exchanger.start();
            exchanger.startMessaging();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Client stopped");
    }
}

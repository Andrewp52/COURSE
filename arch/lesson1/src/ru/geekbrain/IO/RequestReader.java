package ru.geekbrain.IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Reading user request from URL
 */
public class RequestReader {
    BufferedReader input;

    public RequestReader(Socket socket) {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readRequest() {
        String request = "";
        try {
            while (!input.ready()) ;
            String firstLine = input.readLine();
            String[] parts = firstLine.split(" ");
            System.out.println(firstLine);
            while (input.ready()) {
                System.out.println(input.readLine());
            }
            request = parts[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }
}

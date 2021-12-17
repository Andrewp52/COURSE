package ru.geekbrain.IO.readers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Reading user request from URL
 */
public class RequestReaderImpl implements RequestReader{
    private Socket socket;
    public RequestReaderImpl(Socket socket) {
        this.socket = socket;
    }

    @Override
    public Deque<String> readRequest() {
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)
            );

            while (!input.ready());
            Deque<String> request = new LinkedList<>();

            while (input.ready()) {
                String line = input.readLine();
                request.add(line);
            }
            return request;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}

package ru.geekbrain.IO.writers;

import ru.geekbrain.util.serializers.Serializer;

import java.net.Socket;

public class WriterFactory {
    public static ResponseWriter getWriter(Socket socket, Serializer serializer){
        return new PrinterWriter(socket, serializer);
    }
}

package ru.geekbrain.IO.writers;

import ru.geekbrain.domain.Response;
import ru.geekbrain.util.serializers.Serializer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Basic output writer
 */
class PrinterWriter implements ResponseWriter{
    private PrintWriter output;
    private Serializer serializer;

    public PrinterWriter(Socket socket, Serializer serializer) {
        try {
            this.output = new PrintWriter(socket.getOutputStream());
            this.serializer = serializer;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void writeResponse(Response response) {
        String res = serializer.serialize(response);
        output.println(res);
        output.flush();
    }
}

package ru.geekbrain.IO;

import ru.geekbrain.headers.ContentType;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

import static ru.geekbrain.IO.ResponseHead.*;

/**
 * Basic output writer
 */
public class PrintResponseWriter {
    PrintWriter output;

    public PrintResponseWriter(Socket socket) {
        try {
            this.output = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeResponse(Path path, ContentType contentType){
        try {
            if (!Files.exists(path)) {
                output.println(getNotFoundHead());
                output.println("<H1>File not found</H1>");
                return;
            }
            output.println(getOkHead(contentType));
            Files.newBufferedReader(path).transferTo(output);
        } catch (IOException e) {
            output.println(getErrorHead());
            e.printStackTrace();
        } finally {
            output.flush();
        }
    }


}

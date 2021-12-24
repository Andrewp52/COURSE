package ru.geekbrain.services;

import ru.geekbrain.IO.FileWorker;
import ru.geekbrain.IO.writers.*;
import ru.geekbrain.domain.*;
import ru.geekbrain.domain.headers.*;
import ru.geekbrain.util.serializers.SerializerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;

public class SocketService implements Service{
    Socket socket;
    FileWorker fileWorker;
    ResponseWriter output;

    public SocketService(Socket socket, FileWorker fileWorker) {
        this.socket = socket;
        this.fileWorker = fileWorker;
    }

    @Override
    public void serveRequest(Request request) {
        // There is might be some logic
        output = WriterFactory.getWriter(socket, SerializerFactory.getSerializer());
        try {
            switch (request.getMethod()){
                case GET -> serveGet(request);
                case PUT -> servePut(request);
                case POST -> servePost(request);
                default -> sendError(HttpStatus.ERROR, "Method not supported");
            }
        } catch (FileNotFoundException e){
            sendError(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IOException e){
            sendError(HttpStatus.ERROR, HttpStatus.ERROR.toString());
        }

    }

    private void serveGet(Request request) throws FileNotFoundException, IOException{
        String body = fileWorker.readFile(request.getUrl());
        sendOk(body);
    }

    public void servePut(Request request) throws FileNotFoundException, IOException{
        fileWorker.writeFile(request.getUrl(), request.getBody());
        String body = fileWorker.readFile(request.getUrl());
        sendOk(body);
    }

    public void servePost(Request request) {
        System.out.println(request.getBody());
        sendOk(request.getBody());
    }


    private void sendOk(String body){
        Response response = Response.Builder.getBuilder()
                .withStatus(HttpStatus.OK)
                .withHeader(HttpHeader.CONTENT_TYPE, ContentType.TEXT_HTML.toString())
                .withBody(body)
                .build();
        output.writeResponse(response);
    }

    private void sendError(HttpStatus status, String message) {
        Response response = Response.Builder.getBuilder()
                .withStatus(status)
                .withHeader(HttpHeader.CONTENT_TYPE, ContentType.TEXT_HTML.toString())
                .withBody(message)
                .build();
        output.writeResponse(response);
    }

    @Override
    public void close() throws IOException {
        if(!this.socket.isClosed()){
            this.socket.close();
        }
    }
}

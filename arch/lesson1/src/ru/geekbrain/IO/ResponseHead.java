package ru.geekbrain.IO;

import ru.geekbrain.headers.ContentType;
import ru.geekbrain.headers.Header;
import ru.geekbrain.headers.HttpStatus;

import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Head for all responses.
 * Contains status & headers list.
 */
public class ResponseHead {
    private HttpStatus status;
    private List<Header> headers;

    {
        this.headers = new LinkedList<>();
    }

    public ResponseHead(HttpStatus status) {
        this.status = status;
    }

    public void addHeader(Header header){
        this.headers.add(header);
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(System.lineSeparator(), "", System.lineSeparator());
        sj.add(status.toString());
        headers.forEach(header -> sj.add(header.toString()));
        return sj.toString();
    }

    // ==================================== DEFAULT HEADS ==========================================

    public static ResponseHead getNotFoundHead(){
        ResponseHead head = new ResponseHead(HttpStatus.NOT_FOUND);
        head.addHeader(ContentType.TEXT_HTML);
        return head;
    }

    public static ResponseHead getErrorHead(){
        return new ResponseHead(HttpStatus.ERROR);
    }

    public static ResponseHead getOkHead(ContentType contentType){
        ResponseHead head = new ResponseHead(HttpStatus.OK);
        head.addHeader(contentType);
        return head;
    }
}

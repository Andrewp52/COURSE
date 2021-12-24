package ru.geekbrain.domain;

import ru.geekbrain.domain.headers.HttpStatus;
import ru.geekbrain.domain.headers.HttpHeader;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private HttpStatus status;
    private Map<HttpHeader, String> headers;
    private String body;

    public Response(HttpStatus status, Map<HttpHeader, String> headers, String body) {
        this.status = status;
        this.headers = headers;
        this.body = body;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Map<HttpHeader, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }


    public static class Builder {
        private HttpStatus status;
        private Map<HttpHeader, String> headers = new HashMap<>();
        private String body;

        public static Builder getBuilder(){
            return new Builder();
        }

        public Builder withStatus(HttpStatus status){
            this.status = status;
            return this;
        }

        public Builder withHeader(HttpHeader header, String value){
            this.headers.put(header, value);
            return this;
        }

        public Builder withHeaders(Map<HttpHeader, String> headers){
            this.headers = headers;
            return this;
        }

        public Builder withBody(String body){
            this.body = body;
            return this;
        }

        public Response build(){
            if(status == null){
                throw new IllegalArgumentException("Status is null");
            }
            return new Response(status, headers, body);
        }
    }

}

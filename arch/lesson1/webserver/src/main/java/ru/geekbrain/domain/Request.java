package ru.geekbrain.domain;

import ru.geekbrain.domain.headers.HttpMethod;
import ru.geekbrain.domain.headers.HttpHeader;

import java.util.Map;

public class Request {
    private HttpMethod method;
    private String url;
    private Map<HttpHeader, String> headers;
    private String body;

    private Request(HttpMethod method, String url, Map<HttpHeader, String> headers, String body) {
        this.method = method;
        this.url = url;
        this.headers = headers;
        this.body = body;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public Map<HttpHeader, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public static class Builder{
        private HttpMethod method;
        private String url;
        private Map<HttpHeader, String> headers;
        private String body;

        public static Builder getBuilder(){
            return new Builder();
        }

        public Builder withMethod(HttpMethod method){
            this.method = method;
            return this;
        }

        public Builder withUrl(String url){
            this.url = url;
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

        public Request build(){
            return new Request(method, url, headers, body);
        }
    }
}

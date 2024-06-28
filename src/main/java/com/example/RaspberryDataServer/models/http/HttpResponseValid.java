package com.example.RaspberryDataServer.models.http;

public class HttpResponseValid extends HttpResponse {
    private Object data;

    public HttpResponseValid(int status, Object data) {
        super(status);
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}

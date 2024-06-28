package com.example.RaspberryDataServer.models.http;

public class HttpResponseInvalid extends HttpResponse {

    private String message;
    public HttpResponseInvalid(int status, String message) {
        super(status);
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}

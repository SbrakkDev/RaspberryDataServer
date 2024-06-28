package com.example.RaspberryDataServer.models.http;

public class HttpResponse {
    private int status;


    public HttpResponse(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
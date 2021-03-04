package com.fico.todo.model.response;

public class BaseApiResponse {
    private String version;
    private Object response;
    private String statusCode;
    private String statusMessage;

    public BaseApiResponse(String statusCode, String statusMessage, String version) {
        this.version = version;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public BaseApiResponse(){

    }

    public BaseApiResponse(String version, Object response, String statusCode, String statusMessage) {
        this.version = version;
        this.response = response;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}

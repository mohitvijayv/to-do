package com.fico.todo.model;

import java.util.List;

public class TaskApiResponse {
    private String statusCode;
    private String statusMessage;
    private String version;
    private List<Task> response;

    public TaskApiResponse(String statusCode, String statusMessage, String version, List<Task> response) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.version = version;
        this.response = response;
    }

    public TaskApiResponse(String statusCode, String statusMessage, String version) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.version = version;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Task> getResponse() {
        return response;
    }

    public void setResponse(List<Task> response) {
        this.response = response;
    }
}

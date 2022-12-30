package com.leodesanmiguel.fichadocente.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public class ErrorInfo {
    @JsonProperty("message")
    private String message;
    @JsonProperty("status_code")
    private HttpStatus statusCode;
    @JsonProperty("uri")
    private String uriRequested;

    public ErrorInfo(UserNotFoundException exception, String uriRequested) {
        this.message = exception.getMessage();
        this.statusCode = HttpStatus.NOT_FOUND;
        this.uriRequested = uriRequested;
    }

    public ErrorInfo(HttpStatus statusCode, String message, String uriRequested) {
        this.message = message;
        this.statusCode = statusCode;
        this.uriRequested = uriRequested;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getUriRequested() {
        return uriRequested;
    }
}

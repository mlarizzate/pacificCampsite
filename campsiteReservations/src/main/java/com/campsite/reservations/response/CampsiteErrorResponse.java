package com.campsite.reservations.response;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class CampsiteErrorResponse {
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public CampsiteErrorResponse(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public CampsiteErrorResponse(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "CampsiteErrorResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", errors=" + errors +
                '}';
    }
}

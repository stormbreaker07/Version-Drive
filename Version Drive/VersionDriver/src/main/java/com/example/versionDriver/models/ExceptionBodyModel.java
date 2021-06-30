package com.example.versionDriver.models;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.Instant;

public class ExceptionBodyModel {

    private String message;
    private HttpStatus status;
    private Timestamp timestamp;

    public ExceptionBodyModel() {
        timestamp = Timestamp.from(Instant.now());
    }

    public ExceptionBodyModel(HttpStatus status) {
        this();
        this.status = status;
    }

    public ExceptionBodyModel(HttpStatus status , String message) {
        this();
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}

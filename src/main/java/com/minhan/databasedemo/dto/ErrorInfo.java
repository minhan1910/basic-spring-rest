package com.minhan.databasedemo.dto;

import org.springframework.http.HttpStatus;

public class ErrorInfo {
    private String reason;
    private HttpStatus httpStatus;
    private String status;

    private ErrorInfo() {
        this.reason = this.httpStatus.getReasonPhrase();
        this.status = this.httpStatus.getReasonPhrase();
    }

    public ErrorInfo(String status, HttpStatus httpStatus, String reason) {
        this.status = status;
        this.httpStatus = httpStatus;
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    public int getHttpStatus() {
        return httpStatus.value();
    }
}

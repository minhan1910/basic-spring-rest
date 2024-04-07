package com.minhan.databasedemo.exceptions.base;

import com.minhan.databasedemo.dto.ErrorInfo;
import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {
    private HttpStatus httpStatus;
    private String status;

    protected BaseException() {
        this(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.status = this.httpStatus.getReasonPhrase();
    }

    protected BaseException(String message) {
        super(message);
    }

    public BaseException(HttpStatus httpStatus, String status, String message) {
        this(message);
        this.httpStatus = httpStatus;
        this.status = status;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getStatus() {
        return status;
    }

    public ErrorInfo toErrorInfo() {
        return new ErrorInfo(this.status, this.httpStatus, getMessage());
    }
}

package com.minhan.databasedemo.exceptions.custom;

import com.minhan.databasedemo.exceptions.base.BaseException;
import org.springframework.http.HttpStatus;

public class DuplicateEmailStudentException extends BaseException {
    public DuplicateEmailStudentException() {
        super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), "Duplicate Email.");
    }
}

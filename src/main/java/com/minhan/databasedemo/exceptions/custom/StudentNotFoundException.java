package com.minhan.databasedemo.exceptions.custom;

import com.minhan.databasedemo.exceptions.base.BaseException;
import org.springframework.http.HttpStatus;

public class StudentNotFoundException extends BaseException {
    public StudentNotFoundException() {
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase(), "Student not found.");
    }
}

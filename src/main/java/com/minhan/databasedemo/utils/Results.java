package com.minhan.databasedemo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

public final class Results {
    public static ResponseEntity ok(Object body) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(body);
    }
}

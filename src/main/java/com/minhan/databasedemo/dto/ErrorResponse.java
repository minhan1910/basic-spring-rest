package com.minhan.databasedemo.dto;

import java.util.Map;

public class ErrorResponse<T> {

    private Map<String, T> response;

    private static final String ERROR_KEY = "errors";

    private ErrorResponse(T value) {
        this.response = Map.of(ERROR_KEY, value);
    }

    private Map<String, T> getResponse() {
        return response;
    }

    public static <T> Map<String, T> of(T value) {
        return new ErrorResponse<T>(value).getResponse();
    }
}

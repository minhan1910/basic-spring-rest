package com.minhan.databasedemo.components.exception;

import com.minhan.databasedemo.dto.ErrorInfo;
import com.minhan.databasedemo.dto.ErrorResponse;
import com.minhan.databasedemo.exceptions.custom.DuplicateEmailStudentException;
import com.minhan.databasedemo.exceptions.custom.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, ErrorInfo>> handleStudentNotFoundException(StudentNotFoundException exception) {
        return mapToResponseEntity(exception.toErrorInfo());
    }

    @ExceptionHandler(DuplicateEmailStudentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, ErrorInfo>> handleStudentNotFoundException(DuplicateEmailStudentException exception) {
        return mapToResponseEntity(exception.toErrorInfo());
    }

//    @ExceptionHandler(BaseException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseEntity<Map<String, ErrorInfo>> handleBaseException(BaseException exception) {
//        return mapToResponseEntity(exception.toErrorInfo());
//    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, ErrorInfo>> handleException(Exception exception) {
        var error = new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return mapToResponseEntity(error);
    }

    private ResponseEntity<Map<String, ErrorInfo>> mapToResponseEntity(ErrorInfo errorInfo) {
        return ResponseEntity
                .status(errorInfo.getHttpStatus())
                .body(ErrorResponse.of(errorInfo));
    }
}

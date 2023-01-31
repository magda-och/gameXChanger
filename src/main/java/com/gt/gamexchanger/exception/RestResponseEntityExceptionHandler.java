package com.gt.gamexchanger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Object> handleNoDataFoundException(
            NoDataFoundException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestap", LocalDateTime.now());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoExistingUser.class)
    public ResponseEntity<Object> handleNoExistingUserException(
            NoExistingUser ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestap", LocalDateTime.now());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
//    @ExceptionHandler(value
//            = {IllegalArgumentException.class, IllegalStateException.class})
//    protected ResponseEntity<Object> handleConflict(
//            RuntimeException ex, WebRequest request) {
//        String bodyOfResponse = "This should be application specific";
//        return handleExceptionInternal(ex, bodyOfResponse,
//                new HttpHeaders(), HttpStatus.CONFLICT, request);
//    }
//}
//}

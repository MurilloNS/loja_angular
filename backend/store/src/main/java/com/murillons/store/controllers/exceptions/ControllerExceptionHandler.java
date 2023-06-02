package com.murillons.store.controllers.exceptions;

import com.murillons.store.services.exceptions.DataAlreadyExistException;
import com.murillons.store.services.exceptions.PermissionDeniedException;
import com.murillons.store.services.exceptions.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(DataAlreadyExistException.class)
    public ResponseEntity<StandardError> dataAlreadyExistsException(DataAlreadyExistException e, HttpServletRequest request) {
        StandardError error = StandardError.builder().timestamp(LocalDateTime.now()).status(HttpStatus.CONFLICT.value())
                .error(e.getMessage()).path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<StandardError> userNotExistException(UserNotExistException e, HttpServletRequest request) {
        StandardError error = StandardError.builder().timestamp(LocalDateTime.now()).status(HttpStatus.NOT_FOUND.value())
                .error(e.getMessage()).path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity<StandardError> permissionDeniedException(PermissionDeniedException e, HttpServletRequest request) {
        StandardError error = StandardError.builder().timestamp(LocalDateTime.now()).status(HttpStatus.NOT_FOUND.value())
                .error(e.getMessage()).path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }
}
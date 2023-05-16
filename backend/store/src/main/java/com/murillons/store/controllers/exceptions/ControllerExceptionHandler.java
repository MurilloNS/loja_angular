package com.murillons.store.controllers.exceptions;

import com.murillons.store.services.exceptions.CpfExistsException;
import com.murillons.store.services.exceptions.EmailExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<StandardError> emailExistsException(EmailExistsException e, HttpServletRequest request) {
        StandardError error = StandardError.builder().timestamp(LocalDateTime.now()).status(HttpStatus.CONFLICT.value())
                .error(e.getMessage()).path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(CpfExistsException.class)
    public ResponseEntity<StandardError> cpfExistsException(CpfExistsException e, HttpServletRequest request) {
        StandardError error = StandardError.builder().timestamp(LocalDateTime.now()).status(HttpStatus.CONFLICT.value())
                .error(e.getMessage()).path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}

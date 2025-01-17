package com.backend.backendProyecto.exceptions;

import com.backend.backendProyecto.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorDTO> handleInvalidDataException(InvalidDataException exception) {
        ErrorDTO error = new ErrorDTO();
        error.setMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ResourceNotFoundExeption.class)
    public ResponseEntity<ErrorDTO> handleResourceNotFoundException(ResourceNotFoundExeption exception) {
        ErrorDTO error = new ErrorDTO();
        error.setMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}

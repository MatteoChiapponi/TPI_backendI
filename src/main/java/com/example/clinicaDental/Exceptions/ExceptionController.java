package com.example.clinicaDental.Exceptions;

import com.example.clinicaDental.Exceptions.BadRequestException;
import com.example.clinicaDental.Exceptions.NotFoundException;
import org.hibernate.JDBCException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> procesarBadRequestExceptions(BadRequestException badRequest) {
        return ResponseEntity.badRequest().body(badRequest.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> procesarNotFoundExceptions(NotFoundException notFound) {
        return ResponseEntity.badRequest().body(notFound.getMessage());
    }

    @ExceptionHandler(JDBCException.class)
    public ResponseEntity<String> procesarNotFoundExceptions(JDBCException jdbc) {
        return ResponseEntity.badRequest().body("proba con otro email o matricula");
    }
}

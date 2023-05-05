package com.pragma.usuariomicroservice.adapters.http.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value={CorreoMalFormuladoException.class,
            DocumentoMalFormuladoException.class,
            CelularMalFormuladoException.class,
            FechaNacimientoMalFormatoException.class,
            NoEsMayorDeEdadException.class})
    public ResponseEntity<Object> BadRequestExceptionHandler(RuntimeException ex){
        ApiException apiException = new ApiException(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}

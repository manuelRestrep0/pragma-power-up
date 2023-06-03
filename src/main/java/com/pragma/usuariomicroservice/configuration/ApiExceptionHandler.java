package com.pragma.usuariomicroservice.configuration;

import com.pragma.usuariomicroservice.adapters.http.exceptions.CelularMalFormuladoException;
import com.pragma.usuariomicroservice.adapters.http.exceptions.CorreoMalFormuladoException;
import com.pragma.usuariomicroservice.adapters.http.exceptions.DocumentoMalFormuladoException;
import com.pragma.usuariomicroservice.adapters.http.exceptions.FechaNacimientoMalFormatoException;
import com.pragma.usuariomicroservice.adapters.http.exceptions.NoEsMayorDeEdadException;
import com.pragma.usuariomicroservice.domain.exceptions.UsuarioNoSeEncuentraRegistradoException;
import com.pragma.usuariomicroservice.domain.exceptions.UsuarioYaExistenteException;
import com.pragma.usuariomicroservice.domain.exceptions.UsuarioNoAutorizadoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.pragma.usuariomicroservice.configuration.Constants.RESPONSE_ERROR_MESSAGE_KEY;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value={CorreoMalFormuladoException.class,
            DocumentoMalFormuladoException.class,
            CelularMalFormuladoException.class,
            FechaNacimientoMalFormatoException.class,
            NoEsMayorDeEdadException.class,
            UsuarioYaExistenteException.class,
            UsuarioNoSeEncuentraRegistradoException.class,})
    public ResponseEntity<Object> BadRequestExceptionHandler(RuntimeException ex){
        ApiException apiException = new ApiException(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value={UsuarioNoAutorizadoException.class})
    public ResponseEntity<Object> NoAutorizadoExceptionHandler(RuntimeException ex){
        ApiException apiException = new ApiException(
                ex.getMessage(),
                HttpStatus.METHOD_NOT_ALLOWED,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                errorMessages.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            } else {
                errorMessages.add(error.getDefaultMessage());
            }
        }
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleAuthenticationException(AuthenticationException noDataFoundException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, noDataFoundException.getMessage()));
    }

}

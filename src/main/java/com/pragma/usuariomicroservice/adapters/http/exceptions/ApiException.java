package com.pragma.usuariomicroservice.adapters.http.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
public class ApiException {
    private final String message;
    private final HttpStatus status;
    private final ZonedDateTime timestamp;

}

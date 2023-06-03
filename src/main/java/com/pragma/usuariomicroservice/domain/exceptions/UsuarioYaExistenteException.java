package com.pragma.usuariomicroservice.domain.exceptions;

public class UsuarioYaExistenteException extends RuntimeException{

    public UsuarioYaExistenteException(String message) {
        super(message);
    }
}

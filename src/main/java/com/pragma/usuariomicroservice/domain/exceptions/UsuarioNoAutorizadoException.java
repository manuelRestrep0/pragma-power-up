package com.pragma.usuariomicroservice.domain.exceptions;

public class UsuarioNoAutorizadoException extends RuntimeException{
    public UsuarioNoAutorizadoException(String message) {
        super(message);
    }
}

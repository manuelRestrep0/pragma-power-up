package com.pragma.usuariomicroservice.adapters.jpa.mysql.exceptions;

public class UsuarioYaExistenteException extends RuntimeException{

    public UsuarioYaExistenteException(String message) {
        super(message);
    }
}

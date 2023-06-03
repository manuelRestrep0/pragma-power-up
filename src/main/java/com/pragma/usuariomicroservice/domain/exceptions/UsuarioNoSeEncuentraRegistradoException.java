package com.pragma.usuariomicroservice.domain.exceptions;

public class UsuarioNoSeEncuentraRegistradoException extends RuntimeException{

    public UsuarioNoSeEncuentraRegistradoException(String message) {
        super(message);
    }
}

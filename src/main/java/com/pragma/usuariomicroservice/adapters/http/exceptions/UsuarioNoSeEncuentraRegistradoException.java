package com.pragma.usuariomicroservice.adapters.http.exceptions;

public class UsuarioNoSeEncuentraRegistradoException extends RuntimeException{

    public UsuarioNoSeEncuentraRegistradoException(String message) {
        super(message);
    }
}

package com.pragma.usuariomicroservice.domain.usecase;

import com.pragma.usuariomicroservice.configuration.Constants;
import com.pragma.usuariomicroservice.domain.exceptions.UsuarioNoAutorizadoException;

public class ValidacionPermisos {
    public ValidacionPermisos() {
    }
    public void validarRol(String rolUsuario, String rolAutorizado){
        if(!rolUsuario.equals(rolAutorizado)){
            throw new UsuarioNoAutorizadoException(Constants.USUARIO_NO_AUTORIZADO);
        }
    }
}

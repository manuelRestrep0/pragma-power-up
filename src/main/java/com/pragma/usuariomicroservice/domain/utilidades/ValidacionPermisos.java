package com.pragma.usuariomicroservice.domain.utilidades;

import com.pragma.usuariomicroservice.domain.exceptions.UsuarioNoAutorizadoException;

public class ValidacionPermisos {
    private ValidacionPermisos(){
        throw new IllegalStateException("Utility class");
    }
    public static void validarRol(String rolUsuario, String rolAutorizado){
        if(!rolUsuario.equals(rolAutorizado)){
            throw new UsuarioNoAutorizadoException(Constantes.USUARIO_NO_AUTORIZADO);
        }
    }
}

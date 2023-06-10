package com.pragma.usuariomicroservice.domain.api;

import com.pragma.usuariomicroservice.domain.model.Usuario;

public interface IUsuarioServicePort {

    void guardarPropietario(Usuario usuario);
    void guardarCliente(Usuario usuario);
    void guardarEmpleado(Usuario usuario, Long idRestaurante);
    Boolean validarPropietario(Long id);
    String obtenerCorreoFromUsuario(Long idUsuario);
}

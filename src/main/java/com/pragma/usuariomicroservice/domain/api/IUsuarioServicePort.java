package com.pragma.usuariomicroservice.domain.api;

import com.pragma.usuariomicroservice.domain.model.Usuario;

public interface IUsuarioServicePort {

    void guardarPropietario(Usuario usuario);
    void guardarCliente(Usuario usuario);
    void guardarEmpleado(Usuario usuario);

    void deleteUsuario(Usuario usuario);

    Usuario getUsuario(Long id);
    Boolean validarPropietario(Long id);
}

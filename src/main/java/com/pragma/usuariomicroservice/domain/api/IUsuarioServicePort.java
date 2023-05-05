package com.pragma.usuariomicroservice.domain.api;

import com.pragma.usuariomicroservice.domain.model.Usuario;

public interface IUsuarioServicePort {

    void saveUsuario(Usuario usuario);

    void deleteUsuario(Usuario usuario);

    Usuario getUsuario(Long id);
}

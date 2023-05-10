package com.pragma.usuariomicroservice.domain.spi;

import com.pragma.usuariomicroservice.domain.model.Usuario;

public interface IUsuarioPersistencePort {

    void guardarUsuario(Usuario usuario);

    void deleteUsuario(Usuario usuario);

    Usuario getUsuario(Long id);
}

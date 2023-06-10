package com.pragma.usuariomicroservice.domain.spi;

import com.pragma.usuariomicroservice.domain.model.Usuario;

import java.util.Optional;

public interface IUsuarioPersistencePort {

    Long guardarUsuario(Usuario usuario);
    void deleteUsuario(Usuario usuario);
    Optional<Usuario> getUsuario(Long id);
    Boolean usuarioCorreoExiste(String correo);
    Boolean usuarioDocumentoExiste(String documento);
    String obtenerCorreoFromUsuario(Long idUsuario);
}

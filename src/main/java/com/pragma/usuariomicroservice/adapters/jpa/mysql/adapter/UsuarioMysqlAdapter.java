package com.pragma.usuariomicroservice.adapters.jpa.mysql.adapter;

import com.pragma.usuariomicroservice.adapters.jpa.mysql.entity.UsuarioEntity;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.repository.IUsuarioRepository;
import com.pragma.usuariomicroservice.domain.model.Usuario;
import com.pragma.usuariomicroservice.domain.spi.IUsuarioPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioMysqlAdapter implements IUsuarioPersistencePort {
    private final IUsuarioRepository usuarioRepository;
    @Override
    public void saveUsuario(Usuario usuario) {
        //TODO: mapper de domain a entity
        this.usuarioRepository.save(new UsuarioEntity());
    }

    @Override
    public void deleteUsuario(Usuario usuario) {

    }
}

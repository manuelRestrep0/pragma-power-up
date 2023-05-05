package com.pragma.usuariomicroservice.adapters.jpa.mysql.adapter;

import com.pragma.usuariomicroservice.adapters.http.exceptions.DocumentoMalFormuladoException;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.entity.RolEntity;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.entity.UsuarioEntity;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.mapper.UsuarioEntityMapper;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.repository.IRolRepository;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.repository.IUsuarioRepository;
import com.pragma.usuariomicroservice.configuration.Constants;
import com.pragma.usuariomicroservice.domain.model.Usuario;
import com.pragma.usuariomicroservice.domain.spi.IUsuarioPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UsuarioMysqlAdapter implements IUsuarioPersistencePort {
    private final IUsuarioRepository usuarioRepository;
    private final IRolRepository rolRepository;
    private final UsuarioEntityMapper usuarioEntityMapper;
    @Override
    public void saveUsuario(Usuario usuario) {
        Optional<RolEntity> rol = rolRepository.findById(Constants.PROPIETARIO_ROL_ID);
        UsuarioEntity usuarioEntity = usuarioEntityMapper.toEntity(usuario);
        if(!rol.isPresent()){
            throw new DocumentoMalFormuladoException(Constants.DOCUMENTO_MAL_FORMULADO_EXCEPTION);
        }
        usuarioEntity.setIdRol(rol.get());
        this.usuarioRepository.save(usuarioEntity);
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
        this.usuarioRepository.delete(this.usuarioEntityMapper.toEntity(usuario));
    }

    @Override
    public Usuario getUsuario(Long id) {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(id);
        return this.usuarioEntityMapper.toUsuario(usuarioEntity.get());
    }
}

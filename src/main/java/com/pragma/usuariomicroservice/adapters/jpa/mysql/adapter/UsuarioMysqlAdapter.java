package com.pragma.usuariomicroservice.adapters.jpa.mysql.adapter;

import com.pragma.usuariomicroservice.adapters.http.exceptions.UsuarioNoSeEncuentraRegistradoException;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.entity.UsuarioEntity;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.exceptions.UsuarioYaExistenteException;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.mapper.UsuarioEntityMapper;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.repository.IUsuarioRepository;
import com.pragma.usuariomicroservice.configuration.Constants;
import com.pragma.usuariomicroservice.domain.model.Usuario;
import com.pragma.usuariomicroservice.domain.spi.IUsuarioPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UsuarioMysqlAdapter implements IUsuarioPersistencePort {
    private final IUsuarioRepository usuarioRepository;
    private final UsuarioEntityMapper usuarioEntityMapper;
    @Override
    public void guardarUsuario(Usuario usuario) {
        if(usuarioRepository.findUsuarioEntityByCorreo(usuario.getCorreo()).isPresent()){
            throw new UsuarioYaExistenteException(Constants.USUARIO_YA_EXISTE_CORREO);
        }
        if(usuarioRepository.findUsuarioEntityByNumeroDocumento(usuario.getNumeroDocumento()).isPresent()){
            throw new UsuarioYaExistenteException(Constants.USUARIO_YA_EXISTE_DOCUMENTO);
        }
        this.usuarioRepository.save(usuarioEntityMapper.toEntity(usuario));
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
        this.usuarioRepository.delete(this.usuarioEntityMapper.toEntity(usuario));
    }

    @Override
    public Usuario getUsuario(Long id) {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(id);
        if(!usuarioEntity.isPresent()){
            throw new UsuarioNoSeEncuentraRegistradoException(Constants.USUARIO_NO_REGISTRADO);

        }
        return usuarioEntityMapper.toUsuario(usuarioEntity.get());
    }
}

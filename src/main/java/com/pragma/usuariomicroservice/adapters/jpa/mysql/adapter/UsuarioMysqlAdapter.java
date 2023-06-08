package com.pragma.usuariomicroservice.adapters.jpa.mysql.adapter;

import com.pragma.usuariomicroservice.adapters.jpa.mysql.entity.UsuarioEntity;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.mapper.UsuarioEntityMapper;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.repository.IUsuarioRepository;
import com.pragma.usuariomicroservice.domain.model.Usuario;
import com.pragma.usuariomicroservice.domain.spi.IUsuarioPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@RequiredArgsConstructor
public class UsuarioMysqlAdapter implements IUsuarioPersistencePort {
    private final IUsuarioRepository usuarioRepository;
    private final UsuarioEntityMapper usuarioEntityMapper;

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuario.setClave(new BCryptPasswordEncoder().encode(usuario.getClave()));
        this.usuarioRepository.save(usuarioEntityMapper.toEntity(usuario));
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
        this.usuarioRepository.delete(this.usuarioEntityMapper.toEntity(usuario));
    }

    @Override
    public Optional<Usuario> getUsuario(Long id) {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(id);
        return usuarioEntity.map(usuarioEntityMapper::toUsuario);
    }
    @Override
    public Boolean usuarioCorreoExiste(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    @Override
    public Boolean usuarioDocumentoExiste(String documento) {
        return usuarioRepository.existsByNumeroDocumento(documento);
    }

    @Override
    public String obtenerCorreoFromUsuario(Long idUsuario) {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(idUsuario);
        String correoUsuario = "";
        if(usuarioEntity.isPresent()){
            correoUsuario = usuarioEntity.get().getCorreo();
        }
        return correoUsuario;
    }
}

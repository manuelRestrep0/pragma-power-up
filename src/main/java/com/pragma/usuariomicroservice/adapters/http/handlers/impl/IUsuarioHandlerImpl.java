package com.pragma.usuariomicroservice.adapters.http.handlers.impl;

import com.pragma.usuariomicroservice.adapters.http.dto.ValidacionesPropietario;
import com.pragma.usuariomicroservice.adapters.http.dto.request.UsuarioRequestDto;
import com.pragma.usuariomicroservice.adapters.http.dto.response.UsuarioResponseDto;
import com.pragma.usuariomicroservice.adapters.http.handlers.IUsuarioHandler;
import com.pragma.usuariomicroservice.adapters.http.mapper.IUsuarioRequestMapper;
import com.pragma.usuariomicroservice.adapters.http.mapper.IUsuarioResponseMapper;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.entity.RolEntity;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.mapper.RolEntityMapper;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.repository.IRolRepository;
import com.pragma.usuariomicroservice.configuration.Constants;
import com.pragma.usuariomicroservice.domain.api.IUsuarioServicePort;
import com.pragma.usuariomicroservice.domain.model.Rol;
import com.pragma.usuariomicroservice.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IUsuarioHandlerImpl implements IUsuarioHandler {
    private final IUsuarioServicePort usuarioServicePort;
    private final IUsuarioRequestMapper usuarioRequestMapper;
    private final IUsuarioResponseMapper usuarioResponseMapper;
    private final RolEntityMapper rolEntityMapper;
    private final IRolRepository rolRepository;


    @Override
    public void savePropietario(UsuarioRequestDto usuarioRequestDto) {
        ValidacionesPropietario validaciones = new ValidacionesPropietario();
        validaciones.validarCorreo(usuarioRequestDto.getCorreo());
        validaciones.validarDocumento(usuarioRequestDto.getNumeroDocumento());
        validaciones.validarTelefono(usuarioRequestDto.getCelular());
        validaciones.validarFechaNacimientoFormato(usuarioRequestDto.getFechaNacimiento());
        validaciones.validadFechaNacimiento(usuarioRequestDto.getFechaNacimiento());
        Usuario usuario = usuarioRequestMapper.toUsuario(usuarioRequestDto);
        Optional<RolEntity> rolEntity = rolRepository.findById(Constants.PROPIETARIO_ROL_ID);
        if(rolEntity.isPresent()){
            Rol rol = rolEntityMapper.rolEntityToRol(rolEntity.get());
            usuario.setIdRol(rol);
        }
        usuarioServicePort.saveUsuario(usuario);
    }

    @Override
    public void deleteUsuario(UsuarioRequestDto usuarioRequestDto) {
        usuarioServicePort.deleteUsuario(usuarioRequestMapper.toUsuario(usuarioRequestDto));
    }

    @Override
    public UsuarioResponseDto getUsuario(Long id) {
        return usuarioResponseMapper.usuarioToUsuarioResponse(usuarioServicePort.getUsuario(id));
    }
}

package com.pragma.usuariomicroservice.adapters.http.handlers.impl;

import com.pragma.usuariomicroservice.adapters.http.dto.request.UsuarioRequestDto;
import com.pragma.usuariomicroservice.adapters.http.handlers.IUsuarioHandler;
import com.pragma.usuariomicroservice.adapters.http.mapper.IUsuarioRequestMapper;
import com.pragma.usuariomicroservice.domain.api.IUsuarioServicePort;
import com.pragma.usuariomicroservice.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IUsuarioHandlerImpl implements IUsuarioHandler {
    private final IUsuarioServicePort usuarioServicePort;
    private final IUsuarioRequestMapper usuarioRequestMapper;
    @Override
    public void savePropietario(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = usuarioRequestMapper.toUsuario(usuarioRequestDto);
        usuarioServicePort.guardarPropietario(usuario);
    }
    @Override
    public void saveEmpleado(UsuarioRequestDto usuarioRequestDto) {
        usuarioServicePort.guardarEmpleado(usuarioRequestMapper.toUsuario(usuarioRequestDto));
    }
    @Override
    public void saveCliente(UsuarioRequestDto usuarioRequestDto) {
        usuarioServicePort.guardarCliente(usuarioRequestMapper.toUsuario(usuarioRequestDto));
    }
    @Override
    public Boolean validarPropietario(Long id) {
        return usuarioServicePort.validarPropietario(id);
    }
    @Override
    public String obtenerCorreoFromUsuario(Long idUsuario) {
        return usuarioServicePort.obtenerCorreoFromUsuario(idUsuario);
    }
}

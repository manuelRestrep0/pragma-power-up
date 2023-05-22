package com.pragma.usuariomicroservice.adapters.http.handlers;

import com.pragma.usuariomicroservice.adapters.http.dto.request.UsuarioRequestDto;
import com.pragma.usuariomicroservice.adapters.http.dto.response.UsuarioResponseDto;

public interface IUsuarioHandler {

    void savePropietario(UsuarioRequestDto usuarioRequestDto);
    void saveEmpleado(UsuarioRequestDto usuarioRequestDto);
    void saveCliente(UsuarioRequestDto usuarioRequestDto);

    void deleteUsuario(UsuarioRequestDto usuarioRequestDto);

    UsuarioResponseDto getUsuario(Long id);
    Boolean validarPropietario(Long id);
}

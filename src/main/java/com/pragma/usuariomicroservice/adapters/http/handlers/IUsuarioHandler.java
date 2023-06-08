package com.pragma.usuariomicroservice.adapters.http.handlers;

import com.pragma.usuariomicroservice.adapters.http.dto.request.UsuarioRequestDto;

public interface IUsuarioHandler {

    void savePropietario(UsuarioRequestDto usuarioRequestDto);
    void saveEmpleado(UsuarioRequestDto usuarioRequestDto);
    void saveCliente(UsuarioRequestDto usuarioRequestDto);
    Boolean validarPropietario(Long id);
    String obtenerCorreoFromUsuario(Long idUsuario);
}

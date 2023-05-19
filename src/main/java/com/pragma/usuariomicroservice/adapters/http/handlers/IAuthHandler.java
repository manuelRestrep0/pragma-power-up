package com.pragma.usuariomicroservice.adapters.http.handlers;

import com.pragma.usuariomicroservice.adapters.http.dto.request.AuthRequestDto;
import com.pragma.usuariomicroservice.adapters.http.dto.response.JwtResponseDto;

public interface IAuthHandler {
    JwtResponseDto login(AuthRequestDto authRequestDto);
    String obtenerIdUsuario(String token);
    String obtenerRolUsuario(String token);
}

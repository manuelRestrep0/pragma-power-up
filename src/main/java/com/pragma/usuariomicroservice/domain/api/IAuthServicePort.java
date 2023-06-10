package com.pragma.usuariomicroservice.domain.api;

public interface IAuthServicePort {
    String obtenerRolUsuario(String token);
    Long obtenerIdFromToken(String token);
}

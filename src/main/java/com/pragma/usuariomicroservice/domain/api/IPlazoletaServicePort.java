package com.pragma.usuariomicroservice.domain.api;

public interface IPlazoletaServicePort {
    void registrarEmpleado(Long idEmpleado, Long idPropietario, Long idRestaurante);
    boolean verificarPropietarioRestaurante(Long idPropietario, Long idRestaurante);
}

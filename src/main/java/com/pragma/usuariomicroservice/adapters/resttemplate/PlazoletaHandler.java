package com.pragma.usuariomicroservice.adapters.resttemplate;

import com.pragma.usuariomicroservice.domain.api.IPlazoletaServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlazoletaHandler implements IPlazoletaServicePort{

    private final PlazoletaRestTemplate plazoletaRestTemplate;
    @Override
    public void registrarEmpleado(Long idEmpleado, Long idPropietario, Long idRestaurante) {
        plazoletaRestTemplate.registrarEmpleado(idEmpleado, idPropietario, idRestaurante);
    }

    @Override
    public boolean verificarPropietarioRestaurante(Long idPropietario, Long idRestaurante) {
        return plazoletaRestTemplate.validarPropietarioRestaurante(idPropietario, idRestaurante);
    }
}

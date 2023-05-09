package com.pragma.plazoletamicroservice.adapters.driving.http.handlers.impl;

import com.pragma.plazoletamicroservice.adapters.driving.feign.client.UsuarioFeignClient;
import com.pragma.plazoletamicroservice.adapters.driving.http.dto.request.RestauranteRequestDto;
import com.pragma.plazoletamicroservice.adapters.driving.http.handlers.IRestauranteHandler;
import com.pragma.plazoletamicroservice.adapters.driving.http.mapper.IRestauranteRequestMapper;
import com.pragma.plazoletamicroservice.domain.api.IRestauranteServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestauranteHandlerImpl implements IRestauranteHandler {

    private final IRestauranteServicePort restauranteServicePort;
    private final IRestauranteRequestMapper restauranteRequestMapper;
    private final UsuarioFeignClient usuarioFeignClient;
    @Override
    public void crearRestaurante(RestauranteRequestDto restauranteRequestDto) {
        Boolean respuesta = usuarioFeignClient.validarPropietario(restauranteRequestDto.getIdPropietario());
        restauranteServicePort.crearRestaurante(restauranteRequestMapper.toRestaurante(restauranteRequestDto));
    }
}

package com.pragma.plazoletamicroservice.domain.usecase;

import com.pragma.plazoletamicroservice.adapters.driving.feign.client.UsuarioFeignClient;
import com.pragma.plazoletamicroservice.domain.api.IRestauranteServicePort;
import com.pragma.plazoletamicroservice.domain.model.Restaurante;
import com.pragma.plazoletamicroservice.domain.spi.IRestaurantePersistencePort;
import com.pragma.plazoletamicroservice.domain.usecase.validaciones.ValidacionesRestaurante;

public class RestauranteUseCase implements IRestauranteServicePort {
    private final IRestaurantePersistencePort restaurantePersistencePort;
    //private final UsuarioFeignClient feignClient;

    public RestauranteUseCase(IRestaurantePersistencePort restaurantePersistencePort) {
        this.restaurantePersistencePort = restaurantePersistencePort;
    }

    @Override
    public void crearRestaurante(Restaurante restaurante) {
        ValidacionesRestaurante validacionesRestaurante = new ValidacionesRestaurante();
        validacionesRestaurante.validarNombre(restaurante.getNombre());

        this.restaurantePersistencePort.crearRestaurante(restaurante);
    }
}

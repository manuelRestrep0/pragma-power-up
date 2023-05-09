package com.pragma.plazoletamicroservice.domain.usecase;

import com.pragma.plazoletamicroservice.domain.api.IRestauranteServicePort;
import com.pragma.plazoletamicroservice.domain.model.Restaurante;
import com.pragma.plazoletamicroservice.domain.spi.IRestaurantePersistencePort;
import com.pragma.plazoletamicroservice.domain.usecase.validaciones.ValidacionesRestaurante;

public class RestauranteUseCase implements IRestauranteServicePort {
    private final IRestaurantePersistencePort restaurantePersistencePort;

    public RestauranteUseCase(IRestaurantePersistencePort restaurantePersistencePort) {
        this.restaurantePersistencePort = restaurantePersistencePort;
    }

    @Override
    public void crearRestaurante(Restaurante restaurante) {
        //TODO: Validaciones
        ValidacionesRestaurante validacionesRestaurante = new ValidacionesRestaurante();
        validacionesRestaurante.validarNombre(restaurante.getNombre());

        this.restaurantePersistencePort.crearRestaurante(restaurante);
    }
}

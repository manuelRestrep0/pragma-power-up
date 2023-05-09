package com.pragma.plazoletamicroservice.domain.usecase;

import com.pragma.plazoletamicroservice.domain.api.IPlatoServicePort;
import com.pragma.plazoletamicroservice.domain.model.Plato;
import com.pragma.plazoletamicroservice.domain.spi.IPlatoPersistencePort;

public class PlatoUseCase implements IPlatoServicePort {
    private final IPlatoPersistencePort platoPersistencePort;

    public PlatoUseCase(IPlatoPersistencePort platoPersistencePort) {
        this.platoPersistencePort = platoPersistencePort;
    }
    @Override
    public void crearPlato(Plato plato) {
        //TODO: Valicaciones
        this.platoPersistencePort.crearPlato(plato);
    }
}

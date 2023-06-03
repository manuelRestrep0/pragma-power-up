package com.pragma.usuariomicroservice.domain.usecase;

import com.pragma.usuariomicroservice.domain.api.IRolServicePort;
import com.pragma.usuariomicroservice.domain.model.Rol;
import com.pragma.usuariomicroservice.domain.spi.IRolPersistencePort;

import java.util.List;

public class RolUseCase implements IRolServicePort {

    private final IRolPersistencePort rolPersistencePort;

    public RolUseCase(IRolPersistencePort rolPersistencePort) {
        this.rolPersistencePort = rolPersistencePort;
    }
    @Override
    public Rol getRol(Long id) {
        return this.rolPersistencePort.getRol(id);
    }

    @Override
    public List<Rol> getRoles() {
        return this.rolPersistencePort.getRoles();
    }
}

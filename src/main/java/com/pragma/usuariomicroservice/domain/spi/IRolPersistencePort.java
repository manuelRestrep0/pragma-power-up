package com.pragma.usuariomicroservice.domain.spi;

import com.pragma.usuariomicroservice.domain.model.Rol;

import java.util.List;

public interface IRolPersistencePort {

    List<Rol> getRoles();
}

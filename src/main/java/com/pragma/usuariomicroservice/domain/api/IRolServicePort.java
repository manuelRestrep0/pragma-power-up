package com.pragma.usuariomicroservice.domain.api;

import com.pragma.usuariomicroservice.domain.model.Rol;

import java.util.List;

public interface IRolServicePort {

    Rol getRol(Long id);

    List<Rol> getRoles();
}

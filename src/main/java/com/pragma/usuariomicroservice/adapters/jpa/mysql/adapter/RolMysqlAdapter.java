package com.pragma.usuariomicroservice.adapters.jpa.mysql.adapter;

import com.pragma.usuariomicroservice.adapters.jpa.mysql.entity.RolEntity;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.mapper.RolEntityMapper;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.repository.IRolRepository;
import com.pragma.usuariomicroservice.domain.model.Rol;
import com.pragma.usuariomicroservice.domain.spi.IRolPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RolMysqlAdapter implements IRolPersistencePort {
    private final IRolRepository rolRepository;
    private final RolEntityMapper rolEntityMapper;

    @Override
    public Rol getRol(Long id) {
        Optional<RolEntity> rolEntity = rolRepository.findById(id);
        if(rolEntity.isEmpty()){
            //exception
        }
        return rolEntityMapper.rolEntityToRol(rolEntity.get());
    }

    @Override
    public List<Rol> getRoles() {
        List<RolEntity> rolEntityList = rolRepository.findAll();
        if(rolEntityList.isEmpty()) {
            // exception
        }
        return rolEntityMapper.toRolList(rolEntityList);
    }
}

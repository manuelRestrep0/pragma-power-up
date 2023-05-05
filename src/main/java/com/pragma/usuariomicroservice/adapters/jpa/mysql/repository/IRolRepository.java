package com.pragma.usuariomicroservice.adapters.jpa.mysql.repository;

import com.pragma.usuariomicroservice.adapters.jpa.mysql.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends JpaRepository<RolEntity, Long> {
}

package com.pragma.usuariomicroservice.adapters.jpa.mysql.repository;

import com.pragma.usuariomicroservice.adapters.jpa.mysql.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {


}

package com.pragma.usuariomicroservice.adapters.jpa.mysql.repository;

import com.pragma.usuariomicroservice.adapters.jpa.mysql.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findUsuarioEntityByCorreo(String correo);
    Optional<UsuarioEntity> findUsuarioEntityByNumeroDocumento(String numeroDocumento);

}

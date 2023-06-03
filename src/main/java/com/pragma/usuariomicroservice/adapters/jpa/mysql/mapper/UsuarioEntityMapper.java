package com.pragma.usuariomicroservice.adapters.jpa.mysql.mapper;

import com.pragma.usuariomicroservice.adapters.jpa.mysql.entity.UsuarioEntity;
import com.pragma.usuariomicroservice.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface UsuarioEntityMapper {

    UsuarioEntity toEntity(Usuario usuario);

    Usuario toUsuario(UsuarioEntity usuarioEntity);
}

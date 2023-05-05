package com.pragma.usuariomicroservice.adapters.jpa.mysql.mapper;

import com.pragma.usuariomicroservice.adapters.jpa.mysql.entity.UsuarioEntity;
import com.pragma.usuariomicroservice.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface UsuarioEntityMapper {

    UsuarioEntity toEntity(Usuario usuario);

    /*
    @Mapping(target = "usuario.id", source = "usuarioEntity.id")
    @Mapping(target = "usuario.nombre", source = "usuarioEntity.nombre")
    @Mapping(target = "usuario.apellido", source = "usuarioEntity.apellido")
    @Mapping(target = "usuario.correo", source = "usuarioEntity.correo")
    @Mapping(target = "usuario.celular", source = "usuarioEntity.celular")
    @Mapping(target = "usuario.numeroDocumento", source = "usuarioEntity.numeroDocumento")
    @Mapping(target = "usuario.fechaNacimiento", source = "usuarioEntity.fechaNacimiento")
    @Mapping(target = "usuario.clave", source = "usuarioEntity.clave")
    @Mapping(target = "usuario.idRol", source = "usuarioEntity.idRol")*/

    //Usuario UsuarioEntityToUsuario(UsuarioEntity usuarioEntity);
    Usuario toUsuario(UsuarioEntity usuarioEntity);
}

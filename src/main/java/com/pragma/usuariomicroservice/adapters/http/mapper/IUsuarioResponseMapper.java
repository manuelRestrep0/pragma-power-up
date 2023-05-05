package com.pragma.usuariomicroservice.adapters.http.mapper;

import com.pragma.usuariomicroservice.adapters.http.dto.response.UsuarioResponseDto;
import com.pragma.usuariomicroservice.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUsuarioResponseMapper {

    /*
    @Mapping(source = "usuario.nombre", target = "usuarioResponseDto.nombre")
    @Mapping(source = "usuario.apellido", target = "usuarioResponseDto.apellido")
    @Mapping(source = "usuario.correo", target = "usuarioResponseDto.correo")
    @Mapping(source = "usuario.celular", target = "usuarioResponseDto.celular")
    @Mapping(source = "usuario.numeroDocumento", target = "usuarioResponseDto.numeroDocumento")
    @Mapping(source = "usuario.fechaNacimiento", target = "usuarioResponseDto.fechaNacimiento")*/
    UsuarioResponseDto usuarioToUsuarioResponse(Usuario usuario);
}

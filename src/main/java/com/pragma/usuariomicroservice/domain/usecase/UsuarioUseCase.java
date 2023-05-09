package com.pragma.usuariomicroservice.domain.usecase;

import com.pragma.usuariomicroservice.adapters.jpa.mysql.entity.RolEntity;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.mapper.RolEntityMapper;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.repository.IRolRepository;
import com.pragma.usuariomicroservice.configuration.Constants;
import com.pragma.usuariomicroservice.domain.api.IUsuarioServicePort;
import com.pragma.usuariomicroservice.domain.model.Rol;
import com.pragma.usuariomicroservice.domain.model.Usuario;
import com.pragma.usuariomicroservice.domain.spi.IUsuarioPersistencePort;
import com.pragma.usuariomicroservice.domain.usecase.validaciones.ValidacionesPropietario;

import java.util.Optional;

public class UsuarioUseCase implements IUsuarioServicePort {

    private final IUsuarioPersistencePort usuarioPersistencePort;
    private final IRolRepository rolRepository;
    private final RolEntityMapper rolEntityMapper;

    public UsuarioUseCase(IUsuarioPersistencePort usuarioPersistencePort, IRolRepository rolRepository, RolEntityMapper rolEntityMapper) {
        this.usuarioPersistencePort = usuarioPersistencePort;
        this.rolRepository = rolRepository;
        this.rolEntityMapper = rolEntityMapper;
    }

    @Override
    public void guardarPropietario(Usuario usuario) {
        ValidacionesPropietario validaciones = new ValidacionesPropietario();
        validaciones.validarFechaNacimientoFormato(usuario.getFechaNacimiento());
        validaciones.validadFechaNacimiento(usuario.getFechaNacimiento());
        Optional<RolEntity> rolEntity = rolRepository.findById(Constants.PROPIETARIO_ROL_ID);
        if(rolEntity.isPresent()){
            Rol rol = rolEntityMapper.rolEntityToRol(rolEntity.get());
            usuario.setIdRol(rol);
        }
        this.usuarioPersistencePort.guardarUsuario(usuario);
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
        this.usuarioPersistencePort.deleteUsuario(usuario);
    }

    @Override
    public Usuario getUsuario(Long id) {
         return this.usuarioPersistencePort.getUsuario(id);
    }

    @Override
    public Boolean validarPropietario(Long id) {
        Usuario usuario = this.usuarioPersistencePort.getUsuario(id);
        return usuario.getIdRol().getId().equals(Constants.PROPIETARIO_ROL_ID);
    }
}

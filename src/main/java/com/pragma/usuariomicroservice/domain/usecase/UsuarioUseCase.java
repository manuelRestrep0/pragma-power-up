package com.pragma.usuariomicroservice.domain.usecase;

import com.pragma.usuariomicroservice.configuration.Constants;
import com.pragma.usuariomicroservice.domain.api.IUsuarioServicePort;
import com.pragma.usuariomicroservice.domain.model.Rol;
import com.pragma.usuariomicroservice.domain.model.Usuario;
import com.pragma.usuariomicroservice.domain.spi.IRolPersistencePort;
import com.pragma.usuariomicroservice.domain.spi.IUsuarioPersistencePort;

public class UsuarioUseCase implements IUsuarioServicePort {

    private final IUsuarioPersistencePort usuarioPersistencePort;
    private final IRolPersistencePort rolPersistencePort;

    public UsuarioUseCase(IUsuarioPersistencePort usuarioPersistencePort, IRolPersistencePort rolPersistencePort) {
        this.usuarioPersistencePort = usuarioPersistencePort;
        this.rolPersistencePort = rolPersistencePort;
    }

    @Override
    public void guardarPropietario(Usuario usuario) {
        Rol rol = rolPersistencePort.getRol(Constants.PROPIETARIO_ROL_ID);
        usuario.setIdRol(rol);
        this.usuarioPersistencePort.guardarUsuario(usuario);
    }

    @Override
    public void guardarCliente(Usuario usuario) {
        Rol rol = rolPersistencePort.getRol(Constants.CLIENTE_ROL_ID);
        usuario.setIdRol(rol);
        this.usuarioPersistencePort.guardarUsuario(usuario);
    }

    @Override
    public void guardarEmpleado(Usuario usuario) {
        //solo el propietario puede crear empleados para su empresa.
        //TODO: hacer conexion con el microservicio de plazoleta para verificar que el restaurante es
        //propiedad del propietario.
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

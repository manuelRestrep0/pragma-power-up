package com.pragma.usuariomicroservice.domain.usecase;

import com.pragma.usuariomicroservice.domain.exceptions.UsuarioNoSeEncuentraRegistradoException;
import com.pragma.usuariomicroservice.domain.exceptions.UsuarioYaExistenteException;
import com.pragma.usuariomicroservice.domain.api.IAuthServicePort;
import com.pragma.usuariomicroservice.domain.api.IUsuarioServicePort;
import com.pragma.usuariomicroservice.domain.model.Rol;
import com.pragma.usuariomicroservice.domain.model.Usuario;
import com.pragma.usuariomicroservice.domain.spi.IRolPersistencePort;
import com.pragma.usuariomicroservice.domain.spi.IUsuarioPersistencePort;

import java.util.Optional;

public class UsuarioUseCase implements IUsuarioServicePort {

    private final IUsuarioPersistencePort usuarioPersistencePort;
    private final IRolPersistencePort rolPersistencePort;
    private final IAuthServicePort authServicePort;

    public UsuarioUseCase(IUsuarioPersistencePort usuarioPersistencePort, IRolPersistencePort rolPersistencePort, IAuthServicePort authServicePort) {
        this.usuarioPersistencePort = usuarioPersistencePort;
        this.rolPersistencePort = rolPersistencePort;
        this.authServicePort = authServicePort;
    }

    @Override
    public void guardarPropietario(Usuario usuario) {

        String rolUsuarioActual = authServicePort.obtenerRolUsuario(Token.getToken());
        ValidacionPermisos.validarRol(rolUsuarioActual,Constantes.ROL_ADMINISTRADOR);

        Rol rol = rolPersistencePort.getRol(Constantes.PROPIETARIO_ROL_ID);
        usuario.setIdRol(rol);
        this.usuarioPersistencePort.guardarUsuario(usuario);
    }

    @Override
    public void guardarCliente(Usuario usuario) {
        //ROL CLIENTE

        validarExistenciaUsuario(usuario.getCorreo(), usuario.getNumeroDocumento());

        Rol rol = rolPersistencePort.getRol(Constantes.CLIENTE_ROL_ID);
        usuario.setIdRol(rol);
        this.usuarioPersistencePort.guardarUsuario(usuario);
    }

    @Override
    public void guardarEmpleado(Usuario usuario) {

        String rolUsuarioActual = authServicePort.obtenerRolUsuario(Token.getToken());
        ValidacionPermisos.validarRol(rolUsuarioActual,Constantes.ROL_PROPIETARIO);

        validarExistenciaUsuario(usuario.getCorreo(), usuario.getNumeroDocumento());

        Rol rol = rolPersistencePort.getRol(Constantes.EMPLEADO_ROL_ID);
        usuario.setIdRol(rol);
        this.usuarioPersistencePort.guardarUsuario(usuario);

    }

    @Override
    public void deleteUsuario(Usuario usuario) {
        this.usuarioPersistencePort.deleteUsuario(usuario);
    }

    @Override
    public Usuario getUsuario(Long id) {
         return obtenerUsuario(usuarioPersistencePort.getUsuario(id));
    }

    @Override
    public Boolean validarPropietario(Long id) {
        Usuario usuario = obtenerUsuario(usuarioPersistencePort.getUsuario(id));
        return usuario.getIdRol().getId().equals(Constantes.PROPIETARIO_ROL_ID);
    }

    private Usuario obtenerUsuario(Optional<Usuario> usuario){
        if(usuario.isEmpty()){
            throw new UsuarioNoSeEncuentraRegistradoException(Constantes.USUARIO_NO_REGISTRADO);
        }
        return usuario.get();
    }
    private void validarExistenciaUsuario(String correo, String documento){
        if(Boolean.TRUE.equals(usuarioPersistencePort.usuarioCorreoExiste(correo))){
            throw new UsuarioYaExistenteException(Constantes.USUARIO_YA_EXISTE_CORREO);
        }
        if(Boolean.TRUE.equals(usuarioPersistencePort.usuarioDocumentoExiste(documento))){
            throw new UsuarioYaExistenteException(Constantes.USUARIO_YA_EXISTE_DOCUMENTO);
        }
    }
}

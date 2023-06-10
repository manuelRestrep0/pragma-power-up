package com.pragma.usuariomicroservice.domain.usecase;

import com.pragma.usuariomicroservice.domain.api.IPlazoletaServicePort;
import com.pragma.usuariomicroservice.domain.exceptions.UsuarioNoAutorizadoException;
import com.pragma.usuariomicroservice.domain.exceptions.UsuarioNoSeEncuentraRegistradoException;
import com.pragma.usuariomicroservice.domain.exceptions.UsuarioYaExistenteException;
import com.pragma.usuariomicroservice.domain.api.IAuthServicePort;
import com.pragma.usuariomicroservice.domain.api.IUsuarioServicePort;
import com.pragma.usuariomicroservice.domain.model.Rol;
import com.pragma.usuariomicroservice.domain.model.Usuario;
import com.pragma.usuariomicroservice.domain.spi.IRolPersistencePort;
import com.pragma.usuariomicroservice.domain.spi.IUsuarioPersistencePort;
import com.pragma.usuariomicroservice.domain.utilidades.Constantes;
import com.pragma.usuariomicroservice.domain.utilidades.Token;
import com.pragma.usuariomicroservice.domain.utilidades.ValidacionPermisos;

import java.util.Optional;

public class UsuarioUseCase implements IUsuarioServicePort {

    private final IUsuarioPersistencePort usuarioPersistencePort;
    private final IRolPersistencePort rolPersistencePort;
    private final IAuthServicePort authServicePort;
    private final IPlazoletaServicePort plazoletaServicePort;

    public UsuarioUseCase(IUsuarioPersistencePort usuarioPersistencePort, IRolPersistencePort rolPersistencePort, IAuthServicePort authServicePort, IPlazoletaServicePort plazoletaServicePort) {
        this.usuarioPersistencePort = usuarioPersistencePort;
        this.rolPersistencePort = rolPersistencePort;
        this.authServicePort = authServicePort;
        this.plazoletaServicePort = plazoletaServicePort;
    }
    @Override
    public void guardarPropietario(Usuario usuario) {
        String rolUsuarioActual = authServicePort.obtenerRolUsuario(Token.getToken());

        ValidacionPermisos.validarRol(rolUsuarioActual, Constantes.ROL_ADMINISTRADOR);

        validarExistenciaUsuario(usuario.getCorreo(), usuario.getNumeroDocumento());

        Rol rol = rolPersistencePort.getRol(Constantes.PROPIETARIO_ROL_ID);
        usuario.setIdRol(rol);
        usuarioPersistencePort.guardarUsuario(usuario);
    }
    @Override
    public void guardarCliente(Usuario usuario) {
        validarExistenciaUsuario(usuario.getCorreo(), usuario.getNumeroDocumento());

        Rol rol = rolPersistencePort.getRol(Constantes.CLIENTE_ROL_ID);
        usuario.setIdRol(rol);
        this.usuarioPersistencePort.guardarUsuario(usuario);
    }
    @Override
    public void guardarEmpleado(Usuario usuario, Long idRestaurante) {
        String rolUsuarioActual = authServicePort.obtenerRolUsuario(Token.getToken());
        ValidacionPermisos.validarRol(rolUsuarioActual,Constantes.ROL_PROPIETARIO);

        validarExistenciaUsuario(usuario.getCorreo(), usuario.getNumeroDocumento());

        Rol rol = rolPersistencePort.getRol(Constantes.EMPLEADO_ROL_ID);
        usuario.setIdRol(rol);

        Long idPropietario = authServicePort.obtenerIdFromToken(Token.getToken());
        if(!plazoletaServicePort.verificarPropietarioRestaurante(idPropietario,idRestaurante)){
           throw new UsuarioNoAutorizadoException("El restaurante no es de este propietario");
        }
        Long idEmpleado = usuarioPersistencePort.guardarUsuario(usuario);

        plazoletaServicePort.registrarEmpleado(idEmpleado,idPropietario,idRestaurante);
    }
    @Override
    public Boolean validarPropietario(Long id) {
        Usuario usuario = obtenerUsuario(usuarioPersistencePort.getUsuario(id));
        return usuario.getIdRol().getId().equals(Constantes.PROPIETARIO_ROL_ID);
    }
    @Override
    public String obtenerCorreoFromUsuario(Long idUsuario) {
        return usuarioPersistencePort.obtenerCorreoFromUsuario(idUsuario);
    }
    public Usuario obtenerUsuario(Optional<Usuario> usuario){
        if(usuario.isEmpty()){
            throw new UsuarioNoSeEncuentraRegistradoException(Constantes.USUARIO_NO_REGISTRADO);
        }
        return usuario.get();
    }
    public void validarExistenciaUsuario(String correo, String documento){
        if(Boolean.TRUE.equals(usuarioPersistencePort.usuarioCorreoExiste(correo))){
            throw new UsuarioYaExistenteException(Constantes.USUARIO_YA_EXISTE_CORREO);
        }
        if(Boolean.TRUE.equals(usuarioPersistencePort.usuarioDocumentoExiste(documento))){
            throw new UsuarioYaExistenteException(Constantes.USUARIO_YA_EXISTE_DOCUMENTO);
        }
    }
}

package com.pragma.usuariomicroservice.domain;

import com.pragma.usuariomicroservice.domain.api.IAuthServicePort;
import com.pragma.usuariomicroservice.domain.exceptions.UsuarioNoAutorizadoException;
import com.pragma.usuariomicroservice.domain.exceptions.UsuarioNoSeEncuentraRegistradoException;
import com.pragma.usuariomicroservice.domain.exceptions.UsuarioYaExistenteException;
import com.pragma.usuariomicroservice.domain.model.Rol;
import com.pragma.usuariomicroservice.domain.model.Usuario;
import com.pragma.usuariomicroservice.domain.spi.IRolPersistencePort;
import com.pragma.usuariomicroservice.domain.spi.IUsuarioPersistencePort;
import com.pragma.usuariomicroservice.domain.usecase.UsuarioUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = UsuarioUseCase.class)
@SpringBootTest
class UsuarioUseCaseTest {
    @MockBean
    IUsuarioPersistencePort usuarioPersistencePort;
    @MockBean
    IRolPersistencePort rolPersistencePort;
    @MockBean
    IAuthServicePort authServicePort;
    @InjectMocks
    @Autowired
    UsuarioUseCase usuarioUseCase;
    Usuario usuario;

    @BeforeEach
    void setUsuario(){
        usuario = new Usuario(
                1L,
                "Juan Manuel",
                "Restrepo",
                "100115443",
                "3024261812",
                "21-07-2001",
                "extrajuan@hotmail.com",
                "clave",
                null
        );
    }

    @Test
    void guardarPropietarioCasoExitoso(){
        when(authServicePort.obtenerRolUsuario(any())).thenReturn("ROLE_ADMINISTRADOR");
        when(usuarioPersistencePort.usuarioCorreoExiste(any())).thenReturn(false);
        when(usuarioPersistencePort.usuarioDocumentoExiste(any())).thenReturn(false);
        when(rolPersistencePort.getRol(any())).thenReturn(new Rol
                (
                        3L,
                        "PROPIETARIO",
                        "ROL DE PROPIETARIO"
                ));

        usuarioUseCase.guardarPropietario(usuario);
        String rolRespuesta = usuario.getIdRol().getNombre();
        String rolEsperado = "PROPIETARIO";

        assertEquals(rolEsperado,rolRespuesta);
    }
    @Test
    void guardarPropietarioRolNoAutorizado(){
        when(authServicePort.obtenerRolUsuario(any())).thenReturn("ROLE_CLIENTE");
        when(usuarioPersistencePort.usuarioCorreoExiste(any())).thenReturn(false);
        when(usuarioPersistencePort.usuarioDocumentoExiste(any())).thenReturn(false);
        when(rolPersistencePort.getRol(any())).thenReturn(new Rol
                (
                        3L,
                        "PROPIETARIO",
                        "ROL DE PROPIETARIO"
                ));

        assertThrows(UsuarioNoAutorizadoException.class, ()-> usuarioUseCase.guardarPropietario(usuario));
    }
    @Test
    void guardarPropietarioCorreoYaRegistrado(){
        when(authServicePort.obtenerRolUsuario(any())).thenReturn("ROLE_ADMINISTRADOR");
        when(usuarioPersistencePort.usuarioCorreoExiste(any())).thenReturn(true);
        when(usuarioPersistencePort.usuarioDocumentoExiste(any())).thenReturn(false);
        when(rolPersistencePort.getRol(any())).thenReturn(new Rol
                (
                        3L,
                        "PROPIETARIO",
                        "ROL DE PROPIETARIO"
                ));

        assertThrows(UsuarioYaExistenteException.class, () -> usuarioUseCase.guardarPropietario(usuario));
    }
    @Test
    void guardarPropietarioDocumentoYaRegistrado(){
        when(authServicePort.obtenerRolUsuario(any())).thenReturn("ROLE_ADMINISTRADOR");
        when(usuarioPersistencePort.usuarioCorreoExiste(any())).thenReturn(false);
        when(usuarioPersistencePort.usuarioDocumentoExiste(any())).thenReturn(true);
        when(rolPersistencePort.getRol(any())).thenReturn(new Rol
                (
                        3L,
                        "PROPIETARIO",
                        "ROL DE PROPIETARIO"
                ));

        assertThrows(UsuarioYaExistenteException.class, () -> usuarioUseCase.guardarPropietario(usuario));
    }
    @Test
    void guardarClienteCasoExitoso(){
        when(usuarioPersistencePort.usuarioCorreoExiste(any())).thenReturn(false);
        when(usuarioPersistencePort.usuarioDocumentoExiste(any())).thenReturn(false);
        when(rolPersistencePort.getRol(any())).thenReturn(new Rol
                (
                        1L,
                        "CLIENTE",
                        "ROL DE CLIENTE"
                ));

        usuarioUseCase.guardarCliente(usuario);
        String rolRespuesta = usuario.getIdRol().getNombre();
        String rolEsperado = "CLIENTE";

        assertEquals(rolEsperado,rolRespuesta);
    }
    @Test
    void guardarClienteCorreoYaRegistrado(){
        when(usuarioPersistencePort.usuarioCorreoExiste(any())).thenReturn(true);
        when(usuarioPersistencePort.usuarioDocumentoExiste(any())).thenReturn(false);
        when(rolPersistencePort.getRol(any())).thenReturn(new Rol
                (
                        1L,
                        "CLIENTE",
                        "ROL DE CLIENTE"
                ));

        assertThrows(UsuarioYaExistenteException.class, () -> usuarioUseCase.guardarCliente(usuario));
    }
    @Test
    void guardarClienteDocumentoYaRegistrado(){
        when(usuarioPersistencePort.usuarioCorreoExiste(any())).thenReturn(false);
        when(usuarioPersistencePort.usuarioDocumentoExiste(any())).thenReturn(true);
        when(rolPersistencePort.getRol(any())).thenReturn(new Rol
                (
                        1L,
                        "CLIENTE",
                        "ROL DE CLIENTE"
                ));

        assertThrows(UsuarioYaExistenteException.class, () -> usuarioUseCase.guardarCliente(usuario));
    }
    @Test
    void guardarEmpleadoCasoExitoso(){
        when(authServicePort.obtenerRolUsuario(any())).thenReturn("ROLE_PROPIETARIO");
        when(usuarioPersistencePort.usuarioCorreoExiste(any())).thenReturn(false);
        when(usuarioPersistencePort.usuarioDocumentoExiste(any())).thenReturn(false);
        when(rolPersistencePort.getRol(any())).thenReturn(new Rol
                (
                        2L,
                        "EMPLEADO",
                        "ROL DE EMPLEADO"
                ));

        usuarioUseCase.guardarEmpleado(usuario);
        String rolRespuesta = usuario.getIdRol().getNombre();
        String rolEsperado = "EMPLEADO";

        assertEquals(rolEsperado,rolRespuesta);
    }
    @Test
    void guardarEmpleadoRolNoAutorizado(){
        when(authServicePort.obtenerRolUsuario(any())).thenReturn("ROLE_EMPLEADO");
        when(usuarioPersistencePort.usuarioCorreoExiste(any())).thenReturn(false);
        when(usuarioPersistencePort.usuarioDocumentoExiste(any())).thenReturn(false);
        when(rolPersistencePort.getRol(any())).thenReturn(new Rol
                (
                        2L,
                        "EMPLEADO",
                        "ROL DE EMPLEADO"
                ));

        assertThrows(UsuarioNoAutorizadoException.class, () -> usuarioUseCase.guardarEmpleado(usuario));
    }
    @Test
    void guardarEmpleadoCorreoYaRegistrado(){
        when(authServicePort.obtenerRolUsuario(any())).thenReturn("ROLE_PROPIETARIO");
        when(usuarioPersistencePort.usuarioCorreoExiste(any())).thenReturn(true);
        when(usuarioPersistencePort.usuarioDocumentoExiste(any())).thenReturn(true);
        when(rolPersistencePort.getRol(any())).thenReturn(new Rol
                (
                        2L,
                        "EMPLEADO",
                        "ROL DE EMPLEADO"
                ));

        assertThrows(UsuarioYaExistenteException.class, ()-> usuarioUseCase.guardarEmpleado(usuario));
    }
    @Test
    void guardarEmpleadoDocumentoYaRegistrado(){
        when(authServicePort.obtenerRolUsuario(any())).thenReturn("ROLE_PROPIETARIO");
        when(usuarioPersistencePort.usuarioCorreoExiste(any())).thenReturn(false);
        when(usuarioPersistencePort.usuarioDocumentoExiste(any())).thenReturn(true);
        when(rolPersistencePort.getRol(any())).thenReturn(new Rol
                (
                        2L,
                        "EMPLEADO",
                        "ROL DE EMPLEADO"
                ));

        assertThrows(UsuarioYaExistenteException.class, ()-> usuarioUseCase.guardarEmpleado(usuario));
    }
    @Test
    void validarPropietarioUsuarioPropietario(){
        when(usuarioPersistencePort.getUsuario(any())).thenReturn(Optional.of(usuario));
        usuario.setIdRol(new Rol
                (
                        3L,
                        "PROPIETARIO",
                        "ROL DE PROPIETARIO"
                ));

        boolean respuesta = usuarioUseCase.validarPropietario(1L);

        assertTrue(respuesta);
    }
    @Test
    void validarPropietarioUsuarioNoPropietario(){
        when(usuarioPersistencePort.getUsuario(any())).thenReturn(Optional.of(usuario));
        usuario.setIdRol(new Rol
                (
                        2L,
                        "EMPLEADO",
                        "ROL DE EMPLEADO"
                ));

        boolean respuesta = usuarioUseCase.validarPropietario(1L);

        assertFalse(respuesta);
    }
    @Test
    void validarPropietarioUsuarioNoExiste(){
        when(usuarioPersistencePort.getUsuario(any())).thenReturn(Optional.empty());

        assertThrows(UsuarioNoSeEncuentraRegistradoException.class, ()-> usuarioUseCase.validarPropietario(1L));
    }
    @Test
    void obtenerCorreoFromIdUsuario(){
        when(usuarioPersistencePort.obtenerCorreoFromUsuario(any())).thenReturn("extrajuan@hotmail.com");

        String correoRespuesta = usuarioUseCase.obtenerCorreoFromUsuario(1L);
        String correoEsperado = usuario.getCorreo();

        assertEquals(correoEsperado,correoRespuesta);
    }
    @Test
    void obtenerUsuarioFromOptional(){
        Optional<Usuario> usuarioOptional = Optional.of(usuario);

        Usuario usuarioRespuesta = usuarioUseCase.obtenerUsuario(usuarioOptional);

        assertEquals(usuario,usuarioRespuesta);
    }
    @Test
    void obtenerUsuarioFromOptionalVacio(){
        Optional<Usuario> usuarioOptional = Optional.empty();

        assertThrows(UsuarioNoSeEncuentraRegistradoException.class, ()-> usuarioUseCase.obtenerUsuario(usuarioOptional));
    }
    @Test
    void validarExistenciaUsuarioCorreoRegistrado(){
        when(usuarioPersistencePort.usuarioCorreoExiste(any())).thenReturn(true);

        assertThrows(UsuarioYaExistenteException.class, ()-> usuarioUseCase.validarExistenciaUsuario(
                "extrajuan@hotmail.com","122212"
        ));
    }
    @Test
    void validarExistenciaUsuarioDocumentoRegistrado(){
        when(usuarioPersistencePort.usuarioCorreoExiste(any())).thenReturn(false);
        when(usuarioPersistencePort.usuarioDocumentoExiste(any())).thenReturn(true);

        assertThrows(UsuarioYaExistenteException.class, ()-> usuarioUseCase.validarExistenciaUsuario(
                "extrajuan@hotmail.com","122212"
        ));
    }

}

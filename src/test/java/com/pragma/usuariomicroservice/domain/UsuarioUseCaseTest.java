package com.pragma.usuariomicroservice.domain;

import com.pragma.usuariomicroservice.domain.api.IAuthServicePort;
import com.pragma.usuariomicroservice.domain.exceptions.UsuarioNoAutorizadoException;
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


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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
                new Rol()
        );
    }

    @Test
    void crearPropietario(){
        when(authServicePort.obtenerRolUsuario(any())).thenReturn("ROLE_ADMINISTRADOR");
        when(rolPersistencePort.getRol(any())).thenReturn(new Rol());

        usuarioUseCase.guardarPropietario(usuario);

        verify(usuarioPersistencePort).guardarUsuario(usuario);
    }
    @Test
    void crearPropietarioRolNoAutorizado(){
        when(authServicePort.obtenerRolUsuario(any())).thenReturn("ROLE_PROPIETARIO");
        when(rolPersistencePort.getRol(any())).thenReturn(new Rol());

        assertThrows(UsuarioNoAutorizadoException.class, () -> usuarioUseCase.guardarPropietario(usuario));
    }

    @Test
    void validarPropietarioUsuarioPropietario(){
        Long id = 1L;
        Rol rolPropietario = new Rol(
                3L,
                "PROPIETARIO",
                "ROL DE PROPIETARIO"
        );
        usuario.setIdRol(rolPropietario);
        when(usuarioPersistencePort.getUsuario(any())).thenReturn(usuario);

        Boolean respuesta = usuarioUseCase.validarPropietario(id);

        assertTrue(respuesta);
    }
    @Test
    void validarPropietarioUsuarioNOPropietario(){
        Long id = 1L;
        Rol rolPropietario = new Rol(
                1L,
                "CLIENTE",
                "ROL DE CLIENTE"
        );
        usuario.setIdRol(rolPropietario);
        when(usuarioPersistencePort.getUsuario(any())).thenReturn(usuario);

        Boolean respuesta = usuarioUseCase.validarPropietario(id);

        assertFalse(respuesta);
    }

}

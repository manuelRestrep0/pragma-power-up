package com.pragma.usuariomicroservice.domain;

import com.pragma.usuariomicroservice.adapters.http.exceptions.FechaNacimientoMalFormatoException;
import com.pragma.usuariomicroservice.adapters.http.exceptions.NoEsMayorDeEdadException;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.entity.RolEntity;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.mapper.RolEntityMapper;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.repository.IRolRepository;
import com.pragma.usuariomicroservice.domain.model.Rol;
import com.pragma.usuariomicroservice.domain.model.Usuario;
import com.pragma.usuariomicroservice.domain.spi.IUsuarioPersistencePort;
import com.pragma.usuariomicroservice.domain.usecase.UsuarioUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

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
    IRolRepository rolRepository;
    @MockBean
    RolEntityMapper rolEntityMapper;
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

    @ParameterizedTest(name = "Valor: {0} , la funcion debe retornar FechaNacimientoMalFormatoExceptio")
    @DisplayName("Cuando la fecha de nacimiento se envia en un formato diferente a dd-mm-yyyy, la funcion debe " +
            "lanzar una exception de tipo FechaNacimientoMalFormatoExceptio")
    @ValueSource(strings = {"21-07-2025","18-12-2089","26-08-2024","01-01-2024"})
    void crearPropietarioFechaFutura(String fecha){
        usuario.setFechaNacimiento(fecha);

        assertThrows(FechaNacimientoMalFormatoException.class, () -> usuarioUseCase.guardarPropietario(usuario));
    }
    @ParameterizedTest(name = "Valor: {0} , la funcion debe retornar NoEsMayorDeEdadException")
    @DisplayName("Cuando la fecha de nacimiento no corresponde a la de una persona que es mayor de edad" +
            " no se puede crear el usuario con el rol de propietario.")
    @ValueSource(strings = {"21-09-2020","19-05-2014","07-07-2019"})
    void creacionPropietarioNoEsMayorDeEdad(String fecha) {
        usuario.setFechaNacimiento(fecha);

        assertThrows(NoEsMayorDeEdadException.class, () -> usuarioUseCase.guardarPropietario(usuario));
    }

    @Test
    void crearPropietario(){
        when(rolRepository.findById(any())).thenReturn(Optional.of(new RolEntity()));
        when(rolEntityMapper.rolEntityToRol(any())).thenReturn(new Rol());

        usuarioUseCase.guardarPropietario(usuario);

        verify(usuarioPersistencePort).guardarUsuario(usuario);
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

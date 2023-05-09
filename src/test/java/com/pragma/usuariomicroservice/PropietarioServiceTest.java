package com.pragma.usuariomicroservice;

import com.pragma.usuariomicroservice.adapters.http.controller.UsuarioRestController;
import com.pragma.usuariomicroservice.adapters.http.dto.request.UsuarioRequestDto;
import com.pragma.usuariomicroservice.adapters.http.exceptions.CelularMalFormuladoException;
import com.pragma.usuariomicroservice.adapters.http.exceptions.CorreoMalFormuladoException;
import com.pragma.usuariomicroservice.adapters.http.exceptions.DocumentoMalFormuladoException;
import com.pragma.usuariomicroservice.adapters.http.exceptions.FechaNacimientoMalFormatoException;
import com.pragma.usuariomicroservice.adapters.http.exceptions.NoEsMayorDeEdadException;
import com.pragma.usuariomicroservice.adapters.http.handlers.IUsuarioHandler;
import com.pragma.usuariomicroservice.adapters.http.handlers.impl.IUsuarioHandlerImpl;
import com.pragma.usuariomicroservice.adapters.http.mapper.IUsuarioRequestMapper;
import com.pragma.usuariomicroservice.adapters.http.mapper.IUsuarioResponseMapper;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.mapper.RolEntityMapper;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.repository.IRolRepository;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.repository.IUsuarioRepository;
import com.pragma.usuariomicroservice.configuration.Constants;
import com.pragma.usuariomicroservice.domain.api.IUsuarioServicePort;
import com.pragma.usuariomicroservice.domain.model.Rol;
import com.pragma.usuariomicroservice.domain.model.Usuario;
import com.pragma.usuariomicroservice.domain.usecase.UsuarioUseCase;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class PropietarioServiceTest {

    static IUsuarioRepository usuarioRepository;
    static IUsuarioHandler usuarioHandler;
    static UsuarioRestController usuarioRestController;
    static UsuarioUseCase usuarioUseCase;
    static IUsuarioServicePort usuarioServicePort;
    static IUsuarioRequestMapper usuarioRequestMapper;
    static IUsuarioResponseMapper usuarioResponseMapper;
    static RolEntityMapper rolEntityMapper;
    static IRolRepository rolRepository;
    static Validator validator;
    UsuarioRequestDto usuarioRequestDto;
    Usuario usuario;


    @BeforeAll
    public static void setUp(){
        usuarioRepository = mock(IUsuarioRepository.class);
        usuarioServicePort = mock(IUsuarioServicePort.class);
        usuarioRequestMapper = mock(IUsuarioRequestMapper.class);
        rolRepository = mock(IRolRepository.class);
        rolEntityMapper = mock(RolEntityMapper.class);
        usuarioResponseMapper = mock(IUsuarioResponseMapper.class);
        usuarioUseCase = mock(UsuarioUseCase.class);
        usuarioHandler = new IUsuarioHandlerImpl(
                usuarioServicePort,
                usuarioRequestMapper,
                usuarioResponseMapper,
                rolEntityMapper,
                rolRepository
        );
        usuarioRestController = new UsuarioRestController(usuarioHandler);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    public void setUsuarioRequestDto(){

        usuarioRequestDto = new UsuarioRequestDto(
                "Juan Manuel",
                "Restrepo",
                "example@hotmail.com",
                "100111332",
                "3024268112",
                "21-07-2001",
                "12345"
        );
    }

    @Test
    @DisplayName("Test de la correcta creacion de un propietario.")
    void creacionPropietario(){
        ResponseEntity<Map<String,String>> resultado = ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY,Constants.PROPIETARIO_CREADO_MENSAJE)
        );

        ResponseEntity<Map<String,String>> respuesta = usuarioRestController.crearPropietario(usuarioRequestDto);

        assertEquals(resultado,respuesta);

    }

    @ParameterizedTest(name = "Valor: {0} , la funcion debe retornar CorreoMalFormuladoException")
    @DisplayName("Cuando el correo esta mal formulado, es decir, " +
            "no cumple el formato de algo@algo.algo se lanza una exception de" +
            "tipo CorreoMalFormuladoException")
    @ValueSource(strings = {"correo","correo@hotmail","correo.com","a","correo@hotmail."})
    void creacionPropietarioCorreoMalFormato(String correo){
        usuarioRequestDto.setCorreo(correo);

        Set<ConstraintViolation<UsuarioRequestDto>> violations = validator.validate(usuarioRequestDto);

        assertEquals(true, !violations.isEmpty());
        for (ConstraintViolation<UsuarioRequestDto> violation: violations
        ) {
            violation = violations.iterator().next();
            assertEquals("correo",violation.getPropertyPath().toString());
        }
    }
    @ParameterizedTest(name = "Valor: {0} , la funcion debe retornar CelularMalFormuladoException")
    @DisplayName("Cuando el numero de celular no es numerico, posee alguna letra o caracter diferente al '+', tiene menos de 8 caracteres o mas de 13 lanza una exception de " +
            "CelularMalFormuladoException")
    @ValueSource(strings = {"a","123","0000000000000000000","123abc","30242618b"})
    void creacionPropietarioCelularMalFormato(String celular){
        usuarioRequestDto.setCelular(celular);

        Set<ConstraintViolation<UsuarioRequestDto>> violations = validator.validate(usuarioRequestDto);

        assertEquals(true, !violations.isEmpty());
        for (ConstraintViolation<UsuarioRequestDto> violation: violations
             ) {
            violation = violations.iterator().next();
            assertEquals("celular",violation.getPropertyPath().toString());
        }
    }

    @ParameterizedTest(name = "Valor: {0} , la funcion debe retornar DocumentoMalFormuladoException")
    @DisplayName("Cuando el numero de documento tiene algun caracter alfabetico o especial debe lanzar una exception de tipo" +
            "DocumentoMalFormuladoException")
    @ValueSource(strings = {"abs","123b","1.2.3.4","abc1","."})
    void creacionPropietarioDocumentoMalFormato(String documento){
        usuarioRequestDto.setNumeroDocumento(documento);

        Set<ConstraintViolation<UsuarioRequestDto>> violations = validator.validate(usuarioRequestDto);

        assertEquals(true, !violations.isEmpty());
        for (ConstraintViolation<UsuarioRequestDto> violation: violations
        ) {
            violation = violations.iterator().next();
            assertEquals("numeroDocumento",violation.getPropertyPath().toString());
        }

    }
    /*@ParameterizedTest(name = "Valor: {0} , la funcion debe retornar FechaNacimientoMalFormatoExceptio")
    @DisplayName("Cuando la fecha de nacimiento se envia en un formato diferente a dd-mm-yyyy, la funcion debe " +
            "lanzar una exception de tipo FechaNacimientoMalFormatoExceptio")
    @ValueSource(strings = {"21-07","21/07/2001","abc","7-21-2001","21-2001","21-07-2025","1-2-2001"})
    void creacionPropietarioFechaNacimientoMalFormato(String fecha){
        usuarioRequestDto.setFechaNacimiento(fecha);

        assertThrows(FechaNacimientoMalFormatoException.class, () -> usuarioRestController.crearPropietario(usuarioRequestDto));
    }

    @DisplayName("Cuando la fecha de nacimiento no corresponde a la de una persona que es mayor de edad" +
            " no se puede crear el usuario con el rol de propietario.")
    @Test
    void creacionPropietarioNoEsMayorDeEdad(){
        usuarioRequestDto.setFechaNacimiento("21-07-2020");
        usuario = new Usuario(
                1L,
                usuarioRequestDto.getNombre(),
                usuarioRequestDto.getApellido(),
                usuarioRequestDto.getCorreo(),
                usuarioRequestDto.getNumeroDocumento(),
                usuarioRequestDto.getCelular(),
                usuarioRequestDto.getFechaNacimiento(),
                usuarioRequestDto.getClave(),
                new Rol()
        );
        when(usuarioRequestMapper.toUsuario(any())).thenReturn(usuario);


        assertThrows(NoEsMayorDeEdadException.class, () -> usuarioUseCase.guardarPropietario(usuario));
    }
     */




}

package com.pragma.usuariomicroservice.adapters;

import com.pragma.usuariomicroservice.adapters.http.dto.request.UsuarioRequestDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = UsuarioRequestDtoTest.class)
@SpringBootTest
public class UsuarioRequestDtoTest {
    static Validator validator;

    UsuarioRequestDto usuarioRequestDto;

    @BeforeAll
    public static void setUp(){
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
}

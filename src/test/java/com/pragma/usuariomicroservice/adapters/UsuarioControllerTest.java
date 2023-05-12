package com.pragma.usuariomicroservice.adapters;

import com.pragma.usuariomicroservice.adapters.http.controller.UsuarioRestController;
import com.pragma.usuariomicroservice.adapters.http.dto.request.UsuarioRequestDto;
import com.pragma.usuariomicroservice.adapters.http.handlers.IUsuarioHandler;
import com.pragma.usuariomicroservice.configuration.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = UsuarioRestController.class)
@SpringBootTest
class UsuarioControllerTest {
    @MockBean
    IUsuarioHandler usuarioHandler;
    @InjectMocks
    @Autowired
    UsuarioRestController usuarioRestController;
    UsuarioRequestDto usuarioRequestDto;

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
    void crearPropietarioCorrectamente(){
        ResponseEntity<Map<String,String>> respuestaEsperada = ResponseEntity.status(HttpStatus.CREATED).body(
          Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY,Constants.PROPIETARIO_CREADO_MENSAJE)
        );

        ResponseEntity<Map<String,String>> respuestaPeticion = usuarioRestController.crearPropietario(usuarioRequestDto);

        verify(usuarioHandler).savePropietario(usuarioRequestDto);
        assertEquals(respuestaEsperada,respuestaPeticion);
    }
    @Test
    void validarPropietarioRespuestaAfirmativa(){
        Long id = 1L;
        when(usuarioHandler.validarPropietario(any())).thenReturn(true);

        Boolean respuestaPeticion = usuarioRestController.validarRolPropietario(id);

        assertTrue(respuestaPeticion);
    }
    @Test
    void validarPropietarioRespuestaNegativa(){
        Long id = 1L;
        when(usuarioHandler.validarPropietario(any())).thenReturn(false);

        Boolean respuestaPeticion = usuarioRestController.validarRolPropietario(id);

        assertFalse(respuestaPeticion);
    }
}

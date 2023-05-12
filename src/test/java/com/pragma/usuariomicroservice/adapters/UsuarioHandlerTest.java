package com.pragma.usuariomicroservice.adapters;

import com.pragma.usuariomicroservice.adapters.http.dto.request.UsuarioRequestDto;
import com.pragma.usuariomicroservice.adapters.http.handlers.impl.IUsuarioHandlerImpl;
import com.pragma.usuariomicroservice.adapters.http.mapper.IUsuarioRequestMapper;
import com.pragma.usuariomicroservice.adapters.http.mapper.IUsuarioResponseMapper;
import com.pragma.usuariomicroservice.domain.api.IUsuarioServicePort;
import com.pragma.usuariomicroservice.domain.model.Usuario;
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

@ContextConfiguration(classes = IUsuarioHandlerImpl.class)
@SpringBootTest
class UsuarioHandlerTest {
    @MockBean
    IUsuarioServicePort usuarioServicePort;
    @MockBean
    IUsuarioRequestMapper usuarioRequestMapper;
    @MockBean
    IUsuarioResponseMapper usuarioResponseMapper;
    @InjectMocks
    @Autowired
    IUsuarioHandlerImpl usuarioHandler;
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
    void guardarPropietario(){
        when(usuarioRequestMapper.toUsuario(any())).thenReturn(new Usuario());

        usuarioHandler.savePropietario(usuarioRequestDto);

        verify(usuarioServicePort).guardarPropietario(usuarioRequestMapper.toUsuario(usuarioRequestDto));
    }
    @Test
    void validarPropietario(){
        Long id = 1L;

        usuarioHandler.validarPropietario(id);

        verify(usuarioServicePort).validarPropietario(id);
    }

}

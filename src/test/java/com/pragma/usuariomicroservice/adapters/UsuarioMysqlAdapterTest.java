package com.pragma.usuariomicroservice.adapters;

import com.pragma.usuariomicroservice.adapters.jpa.mysql.adapter.UsuarioMysqlAdapter;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.entity.UsuarioEntity;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.exceptions.UsuarioYaExistenteException;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.mapper.UsuarioEntityMapper;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.repository.IUsuarioRepository;
import com.pragma.usuariomicroservice.domain.model.Rol;
import com.pragma.usuariomicroservice.domain.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = UsuarioMysqlAdapter.class)
@SpringBootTest
class UsuarioMysqlAdapterTest {
    @MockBean
    IUsuarioRepository usuarioRepository;
    @MockBean
    UsuarioEntityMapper usuarioEntityMapper;
    @InjectMocks
    @Autowired
    UsuarioMysqlAdapter usuarioMysqlAdapter;
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
    void guardarUsuarioCorreoYaRegistrado(){
        String mail = "extrajuan@hotmail.com";
        when(usuarioRepository.findUsuarioEntityByCorreo(mail)).thenReturn(Optional.of(new UsuarioEntity()));

        assertThrows(UsuarioYaExistenteException.class, () -> usuarioMysqlAdapter.guardarUsuario(usuario));
    }
    @Test
    void guardarUsuarioDocumentoYaRegistrado(){
        String documento = "100115443";
        when(usuarioRepository.findUsuarioEntityByCorreo(usuario.getCorreo())).thenReturn(Optional.empty());
        when(usuarioRepository.findUsuarioEntityByNumeroDocumento(documento)).thenReturn(Optional.of(new UsuarioEntity()));

        assertThrows(UsuarioYaExistenteException.class, () -> usuarioMysqlAdapter.guardarUsuario(usuario));
    }
    @Test
    void guardarUsuarioCorrectamente(){
        when(usuarioRepository.findUsuarioEntityByCorreo(usuario.getCorreo())).thenReturn(Optional.empty());
        when(usuarioRepository.findUsuarioEntityByNumeroDocumento(usuario.getNumeroDocumento())).thenReturn(Optional.empty());
        when(usuarioEntityMapper.toEntity(usuario)).thenReturn(new UsuarioEntity());

        usuarioMysqlAdapter.guardarUsuario(usuario);

        verify(usuarioRepository).save(usuarioEntityMapper.toEntity(usuario));

    }

}

package com.pragma.usuariomicroservice.adapters.jpa.mysql.adapter;

import com.pragma.usuariomicroservice.domain.exceptions.UsuarioNoSeEncuentraRegistradoException;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.entity.UsuarioEntity;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.repository.IUsuarioRepository;
import com.pragma.usuariomicroservice.domain.utilidades.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsuarioNoSeEncuentraRegistradoException {
        UsuarioEntity usuarioEntity = usuarioRepository
                .findUsuarioEntityByCorreo(correo)
                .orElseThrow(() -> new UsuarioNoSeEncuentraRegistradoException(Constantes.USUARIO_NO_REGISTRADO));
        return UserDetailsImpl.build(usuarioEntity);
    }
}

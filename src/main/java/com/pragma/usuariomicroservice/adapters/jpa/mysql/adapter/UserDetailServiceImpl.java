package com.pragma.usuariomicroservice.adapters.jpa.mysql.adapter;

import com.pragma.usuariomicroservice.adapters.http.exceptions.UsuarioNoSeEncuentraRegistradoException;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.entity.RolEntity;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.entity.UsuarioEntity;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.repository.IUsuarioRepository;
import com.pragma.usuariomicroservice.configuration.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsuarioNoSeEncuentraRegistradoException {
        UsuarioEntity usuarioEntity = usuarioRepository
                .findUsuarioEntityByCorreo(correo)
                .orElseThrow(() -> new UsuarioNoSeEncuentraRegistradoException(Constants.USUARIO_NO_REGISTRADO));
        List<RolEntity> roles = new ArrayList<>();
        roles.add(usuarioEntity.getIdRol());
        return UserDetailsImpl.build(usuarioEntity,roles);
    }
}

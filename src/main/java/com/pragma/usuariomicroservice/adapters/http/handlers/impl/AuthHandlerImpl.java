package com.pragma.usuariomicroservice.adapters.http.handlers.impl;

import com.pragma.usuariomicroservice.adapters.http.dto.request.AuthRequestDto;
import com.pragma.usuariomicroservice.adapters.http.dto.response.JwtResponseDto;
import com.pragma.usuariomicroservice.adapters.http.handlers.IAuthHandler;
import com.pragma.usuariomicroservice.configuration.security.jwt.TokenUtils;
import com.pragma.usuariomicroservice.domain.api.IAuthServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthHandlerImpl implements IAuthHandler, IAuthServicePort {

    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;
    @Override
    public JwtResponseDto login(AuthRequestDto authRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDto.getCorreo(),authRequestDto.getPassword())
        );
        String jwt = tokenUtils.createToken(authentication);
        return new JwtResponseDto(jwt);
    }

    @Override
    public String obtenerIdUsuario(String token) {
        return tokenUtils.getIdFromToken(token);
    }
    @Override
    public String obtenerRolUsuario(String token) {
        return tokenUtils.getRolesFromToken(token);
    }
    @Override
    public Long obtenerIdFromToken(String token) {
        return Long.parseLong(tokenUtils.getIdFromToken(token));
    }
}

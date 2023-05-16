package com.pragma.usuariomicroservice.configuration.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.usuariomicroservice.adapters.http.dto.request.AuthDto;
import com.pragma.usuariomicroservice.adapters.jpa.mysql.adapter.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        AuthDto authDto = new AuthDto();
        try{
            authDto = new ObjectMapper().readValue(request.getReader(), AuthDto.class);
        } catch (IOException e){
            logger.error(e.getMessage());
        }

        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                authDto.getCorreo(),
                authDto.getPassword()
        );


        return getAuthenticationManager().authenticate(usernamePAT);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String token = TokenUtils.createToken(userDetails.getNombre(),userDetails.getEmail());

        response.addHeader("Authorization","Bearer "+token);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}

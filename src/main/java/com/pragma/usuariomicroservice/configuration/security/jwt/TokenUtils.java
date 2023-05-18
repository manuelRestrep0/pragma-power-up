package com.pragma.usuariomicroservice.configuration.security.jwt;

import com.pragma.usuariomicroservice.adapters.jpa.mysql.adapter.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class TokenUtils {
    private final static Logger logger = LoggerFactory.getLogger(TokenUtils.class);
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration = 3600000L;

    public String createToken(Authentication authentication){

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl usuario = (UserDetailsImpl) authentication.getPrincipal();
        Long id= usuario.getId();
        String nombre = usuario.getNombre();
        String email = usuario.getEmail();
        String roles = usuario.getAuthorities().iterator().next().getAuthority();
        long expirationTime = expiration * 180;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
        Map<String, Object> extra = new HashMap<>();
        extra.put("id usuario",id);
        extra.put("nombre",nombre);
        extra.put("rol",roles);
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public UsernamePasswordAuthenticationToken getAuthetication(String token){
        try{
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secret.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String email = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(email,null, Collections.emptyList());
        } catch (JwtException e){
            return null;
        }
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("token mal formado");
        } catch (UnsupportedJwtException e) {
            logger.error("token no soportado");
        } catch (ExpiredJwtException e) {
            logger.error("token expirado");
        } catch (IllegalArgumentException e) {
            logger.error("token vacío");
        } catch (SignatureException e) {
            logger.error("fail en la firma");
        }
        return false;
    }
    public String getRolesFromToken(String token) {
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().get("rol",String.class);
    }
}

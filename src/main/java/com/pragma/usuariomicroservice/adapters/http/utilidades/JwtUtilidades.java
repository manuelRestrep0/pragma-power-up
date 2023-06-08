package com.pragma.usuariomicroservice.adapters.http.utilidades;

import com.pragma.usuariomicroservice.domain.utilidades.Token;

public class JwtUtilidades {

    private JwtUtilidades(){
        throw new IllegalStateException("Utility class");
    }

    public static void extraerToken(String header){
        if(header != null && header.startsWith("Bearer ")){
            String jwt = header.substring(7);
            guardarTokenDominio(jwt);
        } else {
            //exception hace falta el jwt
        }
    }
    private static void guardarTokenDominio(String token){
        Token.setToken(token);
    }
}

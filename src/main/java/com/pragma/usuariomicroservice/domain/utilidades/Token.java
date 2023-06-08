package com.pragma.usuariomicroservice.domain.utilidades;

public class Token {
    private static String token;

    public static String getToken() {
        return Token.token;
    }
    public static void setToken(String token) {
        Token.token = token;
    }
}

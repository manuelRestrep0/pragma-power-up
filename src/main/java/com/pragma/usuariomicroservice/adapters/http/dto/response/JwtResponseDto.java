package com.pragma.usuariomicroservice.adapters.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class JwtResponseDto {
    private String token;
    private String roles;

    public JwtResponseDto() {
    }

    public JwtResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

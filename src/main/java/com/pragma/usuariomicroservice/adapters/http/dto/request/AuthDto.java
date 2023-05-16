package com.pragma.usuariomicroservice.adapters.http.dto.request;

import lombok.Data;

@Data
public class AuthDto {
    private String correo;
    private String password;
}

package com.pragma.usuariomicroservice.adapters.http.dto.request;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String correo;
    private String password;
}

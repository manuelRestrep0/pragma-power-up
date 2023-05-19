package com.pragma.usuariomicroservice.adapters.http.controller;

import com.pragma.usuariomicroservice.adapters.http.dto.request.AuthRequestDto;
import com.pragma.usuariomicroservice.adapters.http.dto.response.JwtResponseDto;
import com.pragma.usuariomicroservice.adapters.http.handlers.IAuthHandler;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthHandler authHandler;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody @Valid AuthRequestDto authRequestDto) {
        return new ResponseEntity<>(authHandler.login(authRequestDto), HttpStatus.OK);
    }
    @Hidden
    @GetMapping("obtener-id/{token}")
    public String obtenerIdToken(@PathVariable("token") String token){
        return authHandler.obtenerIdUsuario(token);
    }
    @Hidden
    @GetMapping("obtener-rol/{token}")
    public String obtenerRolToken(@PathVariable("token")String token){
        return authHandler.obtenerRolUsuario(token);
    }

}

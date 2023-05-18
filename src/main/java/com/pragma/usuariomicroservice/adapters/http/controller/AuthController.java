package com.pragma.usuariomicroservice.adapters.http.controller;

import com.pragma.usuariomicroservice.adapters.http.dto.request.AuthRequestDto;
import com.pragma.usuariomicroservice.adapters.http.dto.response.JwtResponseDto;
import com.pragma.usuariomicroservice.adapters.http.handlers.IAuthHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}

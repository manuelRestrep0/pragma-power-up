package com.pragma.usuariomicroservice.adapters.http.controller;

import com.pragma.usuariomicroservice.adapters.http.dto.request.JwtToken;
import com.pragma.usuariomicroservice.adapters.http.dto.request.UsuarioRequestDto;
import com.pragma.usuariomicroservice.adapters.http.handlers.IUsuarioHandler;
import com.pragma.usuariomicroservice.configuration.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
public class UsuarioRestController {

    private final IUsuarioHandler usuarioHandler;

    @Operation(summary = "Agregar un nuevo propietario",
    responses = {
            @ApiResponse(responseCode = "201", description = "Propietario creado",
            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
            @ApiResponse(responseCode = "409", description = "Propietario ya existente",
                    content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))
    })
    @PostMapping("/propietario")
    public ResponseEntity<Map<String, String>> crearPropietario(@Valid @RequestBody UsuarioRequestDto usuarioRequestDto){
        //ROL DE ADMINISTRADOR
        usuarioHandler.savePropietario(usuarioRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY,Constants.PROPIETARIO_CREADO_MENSAJE)
        );
    }
    @GetMapping("/validar-propietario/{id}")
    public Boolean validarRolPropietario(@PathVariable("id") Long id){
        return usuarioHandler.validarPropietario(id);
    }

    @GetMapping("/obtenerToken")
    public JwtToken obtenerToken(@RequestHeader("Authorization") String authorizationHeader) {
        // Aquí procesas el encabezado y extraes el JWT
        String jwt = authorizationHeader.replace("Bearer ", "");
        // Crea un objeto JwtToken y establece el JWT obtenido
        JwtToken token = new JwtToken();
        token.setToken(jwt);
        return token;
    }

}

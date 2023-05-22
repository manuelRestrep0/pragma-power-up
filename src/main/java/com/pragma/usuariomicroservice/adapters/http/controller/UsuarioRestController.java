package com.pragma.usuariomicroservice.adapters.http.controller;

import com.pragma.usuariomicroservice.adapters.http.dto.request.UsuarioRequestDto;
import com.pragma.usuariomicroservice.adapters.http.handlers.IUsuarioHandler;
import com.pragma.usuariomicroservice.configuration.Constants;
import io.swagger.v3.oas.annotations.Hidden;
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
        //TODO: devolver un mensaje mas personalizado a la hora de crear un usuario junto al id de este.
        usuarioHandler.savePropietario(usuarioRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY,Constants.PROPIETARIO_CREADO_MENSAJE)
        );
    }
    @Operation(summary = "Agregar un nuevo empleado",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Empleado creado",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Empleado ya existente",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @PostMapping("/empleado")
    public ResponseEntity<Map<String,String>> crearEmpleado(@Valid @RequestBody UsuarioRequestDto usuarioRequestDto){
        usuarioHandler.saveEmpleado(usuarioRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY,"Empleado creado")
        );
    }
    @Operation(summary = "Agregar un nuevo Cliente",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cliente creado",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Cliente ya existente",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @PostMapping("/cliente")
    public ResponseEntity<Map<String,String>> crearCliente(@Valid @RequestBody UsuarioRequestDto usuarioRequestDto){
        usuarioHandler.saveCliente(usuarioRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY,"Cliente creado")
        );
    }
    @Hidden
    @GetMapping("/validar-propietario/{id}")
    public Boolean validarRolPropietario(@PathVariable("id") Long id){
        return usuarioHandler.validarPropietario(id);
    }


}

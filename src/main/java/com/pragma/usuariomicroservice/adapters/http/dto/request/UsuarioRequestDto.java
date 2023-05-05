package com.pragma.usuariomicroservice.adapters.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UsuarioRequestDto {
    private String nombre;
    private String apellido;
    private String correo;
    private String numeroDocumento;
    private String celular;
    private String fechaNacimiento;
}

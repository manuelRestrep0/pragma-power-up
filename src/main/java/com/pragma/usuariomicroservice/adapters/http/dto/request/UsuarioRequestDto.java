package com.pragma.usuariomicroservice.adapters.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioRequestDto {
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String correo;
    @NotBlank
    private String numeroDocumento;
    @NotBlank
    private String celular;
    @NotBlank
    private String fechaNacimiento;
    @NotBlank
    private String clave;
}

package com.pragma.usuariomicroservice.adapters.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    @Pattern(regexp = "[A-Za-z0-9+_.-]+@(.+\\.[A-Za-z]+)$", message = "El correo debe tener el formato correcto.")
    private String correo;
    @NotBlank
    @Pattern(regexp = "^[0-9]+$", message = "El numero de documento debe ser solo numerico.")
    @Positive(message = "El numero de documento no puede ser negativo.")
    private String numeroDocumento;
    @NotBlank
    @Pattern(regexp = "^(\\+\\d{1,3})?((\\d{1,3})|\\d{1,3})\\d{3,4}\\d{4}$", message = "el numero de celular debe tener el formato correcto.")
    @Size(min = 6, max = 13, message = "el numero de celular debe tener entre 6 y 13 digitos.")
    @Positive(message = "el numero de celular no puede ser negativo")
    private String celular;
    @NotBlank
    @Pattern(regexp = "(0[1-9]|[1-2][0-9]|3[0-1])-(0[1-9]|1[0-2])-(19|20)\\d{2}$|^(0[1-9]|[1-2][0-9])-02-(19|20)([02468][048]|[13579][26])$|^(0[1-9]|[1-2][0-9]|30)-(0[13-9]|1[0-2])-(19|20)\\d{2}$|^(0[1-9]|1[0-9]|2[0-8])-02-(19|20)\\d{2}$|^(31)-(0[13578]|1[02])-(19|20)\\d{2}$|^(29)-(02)-(19|20)([02468][1235679]|[13579][01345789])$")
    private String fechaNacimiento;
    @NotBlank
    private String clave;
}

package com.pragma.plazoletamicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestauranteRequestDto {

    @NotBlank
    private String nombre;
    @NotBlank
    private String direccion;
    @NotBlank
    @Pattern(regexp = "^(\\+\\d{1,3})?((\\d{1,3})|\\d{1,3})\\d{3,4}\\d{4}$")
    @Size(min = 6, max = 13)
    private String telefono;
    @NotBlank
    @URL
    private String urlLogo;
    @NotBlank
    @Positive
    @Pattern(regexp = "^[0-9]+$")
    private String nit;
    @NotNull
    private Long idPropietario;
}

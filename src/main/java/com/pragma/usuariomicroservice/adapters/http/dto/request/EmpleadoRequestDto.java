package com.pragma.usuariomicroservice.adapters.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmpleadoRequestDto extends UsuarioRequestDto{

    private Long idRestaurante;
}

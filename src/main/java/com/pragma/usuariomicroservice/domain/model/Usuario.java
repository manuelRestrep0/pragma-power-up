package com.pragma.usuariomicroservice.domain.model;

import lombok.Data;

public class Usuario {
    private Long id;
    private String nombre;
    private String apellido;
    private String numeroDocumento;
    private String celular;
    private String fechaNacimiento;
    private String correo;
    private String clave;
    private Integer id_rol;
}

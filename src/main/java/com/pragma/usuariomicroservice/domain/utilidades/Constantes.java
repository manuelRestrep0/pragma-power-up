package com.pragma.usuariomicroservice.domain.utilidades;

public class Constantes {
    private Constantes() {
        throw new IllegalStateException("Utility class");
    }

    public static final Long CLIENTE_ROL_ID  = 1L;
    public static final Long EMPLEADO_ROL_ID = 2L;
    public static final Long PROPIETARIO_ROL_ID = 3l;
    public static final Long ADMINISTRADOR_ROL_ID = 4L;
    public static final String ROL_ADMINISTRADOR = "ROLE_ADMINISTRADOR";
    public static final String ROL_PROPIETARIO = "ROLE_PROPIETARIO";
    public static final String USUARIO_NO_REGISTRADO = "El usuario no se encuentra registrado";
    public static final String USUARIO_YA_EXISTE_CORREO = "El correo con el que intenta crear el usuario ya se encuentra registrado.";
    public static final String USUARIO_YA_EXISTE_DOCUMENTO = "El documento con el que intenta crear el usuario ya se encuentra registrado.";
    public static final String USUARIO_NO_AUTORIZADO = "El usuario no tiene permisos para realizar esta peticion";
}

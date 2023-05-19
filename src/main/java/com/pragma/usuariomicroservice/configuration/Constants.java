package com.pragma.usuariomicroservice.configuration;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final Long CLIENTE_ROL_ID  = 1L;
    public static final Long EMPLEADO_ROL_ID = 2L;
    public static final Long PROPIETARIO_ROL_ID = 3l;
    public static final Long ADMINISTRADOR_ROL_ID = 4L;
    public static final String ROL_ADMINISTRADOR = "ROLE_ADMINISTRADOR";
    public static final String USUARIO_NO_REGISTRADO = "El usuario no se encuentra registrado";
    public static final String RESPONSE_MESSAGE_KEY = "mensaje";
    public static final String PROPIETARIO_CREADO_MENSAJE = "El propietario fue creado satisfactoriamente.";
    public static final String USUARIO_YA_EXISTE_CORREO = "El correo con el que intenta crear el usuario ya se encuentra registrado.";
    public static final String USUARIO_YA_EXISTE_DOCUMENTO = "El documento con el que intenta crear el usuario ya se encuentra registrado.";
    public static final String USUARIO_NO_AUTORIZADO = "El usuario no tiene permisos para realizar esta peticion";
    public static final String RESPONSE_ERROR_MESSAGE_KEY = "error";
    public static final String WRONG_CREDENTIALS_MESSAGE = "Wrong credentials";
    public static final String SWAGGER_TITLE_MESSAGE = "User API Pragma Power Up";
    public static final String SWAGGER_DESCRIPTION_MESSAGE = "User microservice";
    public static final String SWAGGER_VERSION_MESSAGE = "1.0.0";
    public static final String SWAGGER_LICENSE_NAME_MESSAGE = "Apache 2.0";
    public static final String SWAGGER_LICENSE_URL_MESSAGE = "http://springdoc.org";
    public static final String SWAGGER_TERMS_OF_SERVICE_MESSAGE = "http://swagger.io/terms/";
}

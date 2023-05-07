package com.pragma.usuariomicroservice.configuration;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final Long CLIENTE_ROL_ID  = 1L;
    public static final Long EMPLEADO_ROL_ID = 2L;
    public static final Long PROPIETARIO_ROL_ID = 3l;

    public static final String CORREO_MAL_FORMULADO_EXCEPTION = "Correo mal formulado, por favor ingrese un correo valido.";
    public static final String DOCUMENTO_MAL_FORMULADO_EXCEPTION = "El numero de documento no es de un formato valido.";
    public static final String CELULAR_MAL_FORMULADO_EXCEPTION = "El numero de celular no es correcto.";
    public static final String CELULAR_LONGITUD_EXCEPTION = "El numero de celular debe tener entre 8 y 13 caracteres contando el caracter '+'.";
    public static final String FECHA_NACIMIENTO_MAL_FORMATO  = "La fecha de nacimiento no tiene el formato correcto.";
    public static final String NO_ES_MAYOR_DE_EDAD = "El propietario debe ser mayor de edad.";
    public static final String RESPONSE_MESSAGE_KEY = "mensaje";
    public static final String PROPIETARIO_CREADO_MENSAJE = "El propietario fue creado satisfactoriamente.";
    public static final String USUARIO_YA_EXISTE_CORREO = "El correo con el que intenta crear el usuario ya se encuentra registrado.";
    public static final String USUARIO_YA_EXISTE_DOCUMENTO = "El documento con el que intenta crear el usuario ya se encuentra registrado.";
    public static final String FECHA_NACIMIENTO_NO_EXISTE = "La fecha de nacimiento que ingreso no es valida porque es una fecha futura.";
    public static final String SWAGGER_TITLE_MESSAGE = "User API Pragma Power Up";
    public static final String SWAGGER_DESCRIPTION_MESSAGE = "User microservice";
    public static final String SWAGGER_VERSION_MESSAGE = "1.0.0";
    public static final String SWAGGER_LICENSE_NAME_MESSAGE = "Apache 2.0";
    public static final String SWAGGER_LICENSE_URL_MESSAGE = "http://springdoc.org";
    public static final String SWAGGER_TERMS_OF_SERVICE_MESSAGE = "http://swagger.io/terms/";
}

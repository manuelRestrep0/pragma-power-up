#  Usuario-microservice

Este microservicio se encarga de las consultas que se hagan con la base de datos donde se almacenan los usuarios y toda la logica de
negocio referente a la creación de usuarios tales como: crear un propietario, crear un empleado, crear un cliente, validar el 
rol de un usuario y entre otras funcionalidades.

El puerto del microservicio es por defecto el 8080 y recuerde modificar los datos de acceso a la base de datos.

URL swagger: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)


## EndPoints: 

### Login: /auth/login POST

Se debe proteger algunos endpoints para que no cualquier usuario pueda realizar cambios en la base de datos, por lo tanto 
se implementó un endpoint para logearse con el correo y contraseña que diligencia en la creación de su usuario.

El endpoint devuelve un token y es necesario utilizar ese token para que se habiliten los endpoints protegidos.

El endpoint recibe de la siguiente manera los datos de inicio de sesión:
```java {.highlight .highlight-source-java .bg-black}
    {
        "correo": [tu direccion de correo electronico],
        "password": [tu contrasena]
    }
```

la petición no se realizara si falta un campo de los anteriores, ademas, se verifica con la base de datos que la contraseña si corresponsa a ese correo 
para devolver el token, de lo contrario se lanzará una exception.

Para probar la funcionalidad de este endpoint, puede crear un usuario de tipo cliente que no requiere permisos y, luego de crearlo correctamente, 
probar el correo y la contraseña que ingresó y debería devolverle un token que, si lo descripta en la pagina jwt.io debe mostrarle información 
que ingresó en la creación del usuario.

### Crear propietario: /usuario/propietario  POST

Este endPoint se encarga de crear el propietario, validar los datos ingresados, asignarle el rol de 
propietario y luego guardar esos datos en la base de datos.


Para crear el propietario recuerde que:

Debe estar logeado con un usuario administrador.

el numero de documento debe ser numerico y positivo, el celular debe ser numerico y el prefijo (+numero del pais) es opcional,
el correo debe ser un correo valido, y la fecha de nacimiento debe ser una fecha no mayor a la actual, con el formato de 
dd-MM-yyyy (21-07-2001) ademas debe haber una diferencia de 18 años entre la fecha ingresada y la fecha actual
que verifica que el propietario a crear sea mayor de edad.

Ejemplo del cuerpo de la peticion: 

```java {.highlight .highlight-source-java .bg-black}
    {
    "nombre": "string",
    "apellido": "string",
    "correo": "string",
    "numeroDocumento": "string",
    "celular": "string",
    "fechaNacimiento": "string",
    "clave": "string"
    }
```

La respuesta que devuelve el endPoint al hacer bien la solicitud es la siguiente: (junto a un estado http 201.)


```java {.highlight .highlight-source-java .bg-black}
{
  "mensaje": "El propietario fue creado satisfactoriamente."
}
```

### Crear Empleado usuario/empleado  POST

Para crear un empleado debe estar logeado con un usuario con el rol de propietario.

Ejemplo del cuerpo de la peticion: 

```java {.highlight .highlight-source-java .bg-black}
    {
    "nombre": "string",
    "apellido": "string",
    "correo": "string",
    "numeroDocumento": "string",
    "celular": "string",
    "fechaNacimiento": "string",
    "clave": "string"
    }
```

### Crear cliente usuario/cliente   POST

Para crear un cliente no necesita estar logeado, solo es necesario llenar todos los campos con los requisitos
obligatiorios para registrar un usuario.

Ejemplo del cuerpo de la peticion: 

```java {.highlight .highlight-source-java .bg-black}
    {
    "nombre": "string",
    "apellido": "string",
    "correo": "string",
    "numeroDocumento": "string",
    "celular": "string",
    "fechaNacimiento": "string",
    "clave": "string"
    }
```


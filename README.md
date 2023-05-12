#  Usuario-microservice

Este microservicio se encarga de las consultas que se hagan con la base de datos donde se almacenan los usuarios y toda la logica de
negocio referente a la creación de usuarios tales como: crear un propietario, crear un empleado, crear un cliente, validar el 
rol de un usuario y entre otras funcionalidades.

El puerto del microservicio es por defecto el 8080 y recuerde modificar los datos de acceso a la base de datos.

URL swagger: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)


## EndPoints: 

### Crear propietario: /usuario/propietario  POST

Este endPoint se encarga de crear el propietario, validar los datos ingresados, asignarle el rol de 
propietario y luego guardar esos datos en la base de datos.


Para crear el propietario recuerde que:


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

package com.pragma.usuariomicroservice.adapters.http.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = FechaNacimientoValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FechaNacimiento {
    String message() default "Formato de fecha de nacimiento inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

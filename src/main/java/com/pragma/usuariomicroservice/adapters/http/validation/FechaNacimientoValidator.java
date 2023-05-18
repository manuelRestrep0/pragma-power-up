package com.pragma.usuariomicroservice.adapters.http.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FechaNacimientoValidator implements ConstraintValidator<FechaNacimiento,String> {

    private static final String FORMATO_FECHA = "dd-MM-yyyy";

    @Override
    public boolean isValid(String fecha, ConstraintValidatorContext context) {
        LocalDate date = stringToDate(fecha);
        if(date.isAfter(LocalDate.now())){
            return false;
        }
        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(date,fechaActual);
        return (periodo.getYears()>=18);
    }
    LocalDate stringToDate(String fecha) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        return LocalDate.parse(fecha,formatter);
    }
}

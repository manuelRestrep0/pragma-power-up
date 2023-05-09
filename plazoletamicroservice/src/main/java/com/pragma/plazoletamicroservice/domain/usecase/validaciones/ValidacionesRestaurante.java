package com.pragma.plazoletamicroservice.domain.usecase.validaciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacionesRestaurante {

    protected Pattern patron;
    protected Matcher matcher;
    protected String expresionRegular;
    protected boolean respuestaValidacion;

    public void validarNombre(String nombreRestaurante){
        expresionRegular = "(?=.*[a-zA-Z])(?=.*[0-9]*)[a-zA-Z0-9\\ ]+$";
        respuestaValidacion = ejecutarMatcher(nombreRestaurante);
        if(!respuestaValidacion){
            //TODO: exception nombre del restaurante no es correcto.
        }

    }
    protected boolean ejecutarMatcher(String validar){
        patron = Pattern.compile(expresionRegular);
        matcher = patron.matcher(validar);
        return matcher.find();
    }
}

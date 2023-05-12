package com.pragma.usuariomicroservice.domain.usecase.validaciones;

import com.pragma.usuariomicroservice.adapters.http.dto.request.UsuarioRequestDto;
import com.pragma.usuariomicroservice.adapters.http.exceptions.FechaNacimientoMalFormatoException;
import com.pragma.usuariomicroservice.configuration.Constants;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class ValidacionesGenerales {

    protected UsuarioRequestDto usuarioRequestDto;
    protected Pattern patron;
    protected Matcher matcher;
    protected String expresionRegular;
    protected boolean respuestaValidacion;

    public ValidacionesGenerales() {
    }

    public void validarFechaNacimientoFormato(String fechaNacimiento){
        LocalDate fecha = stringToDate(fechaNacimiento);
        if(fecha.isAfter(LocalDate.now())){
            throw new FechaNacimientoMalFormatoException(Constants.FECHA_NACIMIENTO_NO_EXISTE);
        }
    }

    protected LocalDate stringToDate(String fecha) throws DateTimeParseException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(fecha,formatter);
    }
}

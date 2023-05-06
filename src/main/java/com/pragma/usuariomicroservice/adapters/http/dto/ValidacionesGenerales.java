package com.pragma.usuariomicroservice.adapters.http.dto;

import com.pragma.usuariomicroservice.adapters.http.dto.request.UsuarioRequestDto;
import com.pragma.usuariomicroservice.adapters.http.exceptions.CelularMalFormuladoException;
import com.pragma.usuariomicroservice.adapters.http.exceptions.CorreoMalFormuladoException;
import com.pragma.usuariomicroservice.adapters.http.exceptions.DocumentoMalFormuladoException;
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



    public void validarCorreo(String correo){
        expresionRegular = "[A-Za-z0-9+_.-]+@(.+\\.[A-Za-z]+)$";
        respuestaValidacion = ejecutarMatcher(correo);
        if(!respuestaValidacion){
            throw new CorreoMalFormuladoException(Constants.CORREO_MAL_FORMULADO_EXCEPTION);
        }

    }
    public void validarDocumento(String documento) {
        expresionRegular = "^[0-9]+$";
        respuestaValidacion = ejecutarMatcher(documento);
        if(!respuestaValidacion){
            throw new DocumentoMalFormuladoException(Constants.DOCUMENTO_MAL_FORMULADO_EXCEPTION);
        }
    }
    public void validarTelefono(String telefono){
        expresionRegular = "^(\\+\\d{1,3})?((\\d{1,3})|\\d{1,3})\\d{3,4}\\d{4}$";
        respuestaValidacion = ejecutarMatcher(telefono);
        if(!respuestaValidacion){
            if(telefono.length()<8 || telefono.length()>13){
                throw new CelularMalFormuladoException(Constants.CELULAR_LONGITUD_EXCEPTION);
            }
            throw new CelularMalFormuladoException(Constants.CELULAR_MAL_FORMULADO_EXCEPTION);
        }
    }

    public void validarFechaNacimientoFormato(String fechaNacimiento){
        stringToDate(fechaNacimiento);
    }

    private boolean ejecutarMatcher(String validar){
        patron = Pattern.compile(expresionRegular);
        matcher = patron.matcher(validar);
        return matcher.find();
    }

    protected LocalDate stringToDate(String fecha){
        DateTimeFormatter formatter = null;
        try{
            formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        } catch (DateTimeParseException e){
            throw new FechaNacimientoMalFormatoException(Constants.FECHA_NACIMIENTO_MAL_FORMATO);
        }
        return LocalDate.parse(fecha,formatter);
    }
}

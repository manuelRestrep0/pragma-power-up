package com.pragma.usuariomicroservice.adapters.resttemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PlazoletaRestTemplate {
    void registrarEmpleado(Long idEmpleado, Long idPropietario, Long idRestaurante){
        String url = "http://localhost:8090/restaurante/registrar-empleado-restaurante";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("idEmpleado", String.valueOf(idEmpleado));
        params.add("idPropietario", String.valueOf(idPropietario));
        params.add("idRestaurante", String.valueOf(idRestaurante));

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        // Envía la solicitud POST
        restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    }
    boolean validarPropietarioRestaurante(Long idPropietario, Long idRestaurante){
        String url = "http://localhost:8090/restaurante/validar-propietario-restaurante";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("idPropietario", idPropietario)
                .queryParam("idRestaurante", idRestaurante);

        ResponseEntity<Boolean> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, Boolean.class);

        return response.getBody();
    }
}

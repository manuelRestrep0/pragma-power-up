package com.pragma.usuariomicroservice.adapters.http.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class JwtToken {
    private String token;
}

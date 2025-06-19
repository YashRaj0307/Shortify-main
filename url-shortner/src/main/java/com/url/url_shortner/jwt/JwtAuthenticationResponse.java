package com.url.url_shortner.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public JwtAuthenticationResponse() {

    }
}

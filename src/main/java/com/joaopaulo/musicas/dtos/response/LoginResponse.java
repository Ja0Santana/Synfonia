package com.joaopaulo.musicas.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String refreshToken;
    @Builder.Default
    private String tipoToken = "Bearer";
    private long expiraEm;
    private UsuarioResponse usuario;
}
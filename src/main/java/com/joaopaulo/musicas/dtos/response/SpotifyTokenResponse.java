package com.joaopaulo.musicas.dtos.response;

public record SpotifyTokenResponse(
    String access_token,
    String token_type,
    Integer expires_in,
    String refresh_token,
    String scope
) {}

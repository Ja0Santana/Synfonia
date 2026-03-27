package com.joaopaulo.musicas.dtos.response;

import java.time.LocalDateTime;

public record HistoricoResponse(
        String id,
        Long userId,
        MusicResponse music,
        LocalDateTime dataReproducao
) {}

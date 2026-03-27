package com.joaopaulo.musicas.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpotifyImportRequest {
    private String spotifyPlaylistId;
    private String accessToken;
}

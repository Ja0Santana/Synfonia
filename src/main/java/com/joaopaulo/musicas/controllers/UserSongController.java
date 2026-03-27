package com.joaopaulo.musicas.controllers;

import com.joaopaulo.musicas.entities.UserSong;
import com.joaopaulo.musicas.enums.MusicSource;
import com.joaopaulo.musicas.services.UserSongService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Gerenciamento de Músicas Salvas", description = "Endpoints para adicionar e listar músicas salvas no perfil")
public class UserSongController {

    private final UserSongService userSongService;

    @Operation(summary = "Adicionar música ao perfil do usuário")
    @PostMapping("/{userId}/songs")
    public ResponseEntity<UserSong> adicionarMusica(@PathVariable("userId") Long userId, @RequestBody com.joaopaulo.musicas.dtos.request.MusicSaveRequest request) {
        UserSong userSong = userSongService.adicionarMusica(userId, request);
        return ResponseEntity.ok(userSong);
    }

    @Operation(summary = "Remover música do perfil do usuário")
    @DeleteMapping("/{userId}/songs/{trackId}")
    public ResponseEntity<Void> removerMusica(@PathVariable("userId") Long userId, @PathVariable("trackId") String trackId) {
        userSongService.removerMusica(userId, trackId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Remover músicas do perfil por fonte (Ex: SPOTIFY)")
    @DeleteMapping("/{userId}/songs/source/{source}")
    public ResponseEntity<Long> removerMusicasPorFonte(@PathVariable("userId") Long userId, @PathVariable("source") MusicSource source) {
        Long count = userSongService.removerMusicasPorFonte(userId, source);
        return ResponseEntity.ok(count);
    }



    @Operation(summary = "Listar todas as músicas salvas por um usuário")
    @GetMapping("/{userId}/songs")
    public ResponseEntity<List<com.joaopaulo.musicas.dtos.response.UserSongResponse>> listarMusicas(@PathVariable("userId") Long userId) {
        List<com.joaopaulo.musicas.dtos.response.UserSongResponse> musicas = userSongService.listarMusicas(userId);
        return ResponseEntity.ok(musicas);
    }

    @Operation(summary = "Verificar se uma música específica está salva no perfil")
    @GetMapping("/{userId}/songs/{trackId}")
    public ResponseEntity<UserSong> verificarMusica(@PathVariable("userId") Long userId, @PathVariable("trackId") String trackId) {
        return userSongService.verificarMusicaSalva(userId, trackId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
package com.joaopaulo.musicas.repositories;

import com.joaopaulo.musicas.entities.UserSong;
import com.joaopaulo.musicas.enums.MusicSource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSongRepository extends MongoRepository<UserSong, String> {

    List<UserSong> findByUserId(Long userId);

    Optional<UserSong> findByUserIdAndTrackId(Long userId, String trackId);

    void deleteByUserIdAndTrackId(Long userId, String trackId);

    Long deleteByUserIdAndSource(Long userId, MusicSource source);


    boolean existsByUserIdAndTrackId(Long userId, String trackId);

    void deleteAllByUserId(Long userId);
}
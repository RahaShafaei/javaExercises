package com.player.playlistapplication.repository;

import com.player.playlistapplication.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfGenreRepository extends JpaRepository<Genre, Long> {
    Genre findByName(String name);
}

package com.player.playlistapplication.repository;

import com.player.playlistapplication.model.Genre;
import com.player.playlistapplication.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfPlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByMusicList_Artist(String artist);

    List<Playlist> findAllByMusicList_Genre(Genre genre);
}

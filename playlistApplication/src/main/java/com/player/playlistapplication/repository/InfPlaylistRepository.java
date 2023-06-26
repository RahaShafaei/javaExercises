package com.player.playlistapplication.repository;

import com.player.playlistapplication.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfPlaylistRepository  extends JpaRepository<Playlist, Long> {
}

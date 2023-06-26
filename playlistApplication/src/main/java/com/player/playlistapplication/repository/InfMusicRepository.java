package com.player.playlistapplication.repository;

import com.player.playlistapplication.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfMusicRepository  extends JpaRepository<Music, Long> {
}

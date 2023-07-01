package com.player.playlistapplication.controller.adjustments;

import com.player.playlistapplication.exception.BadParameterException;
import com.player.playlistapplication.helper.EnmBasedOnSth;
import com.player.playlistapplication.repository.InfMusicRepository;
import com.player.playlistapplication.repository.InfPlaylistRepository;
import org.springframework.stereotype.Component;

@Component
public class Playing {
    private Long id;
    private InfPlaylistRepository playlistRepository;
    private InfMusicRepository musicRepository;

    public Playing(InfPlaylistRepository playlistRepository,
                   InfMusicRepository musicRepository) {
        this.playlistRepository = playlistRepository;
        this.musicRepository = musicRepository;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Player create(EnmBasedOnSth type) {

        Player player = switch (type) {
            case MUSIC -> new MusicPlayer(this.id, this.playlistRepository, this.musicRepository);
            case PLAYLIST -> new PlaylistPlayer(this.id, this.playlistRepository, this.musicRepository);
            default -> throw new BadParameterException("The type is not in defined parameters.");
        };

        return player;
    }
}

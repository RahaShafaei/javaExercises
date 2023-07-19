package com.player.playlistapplication.controller.builder;

import com.player.playlistapplication.controller.PlaylistController;
import com.player.playlistapplication.controller.smartPlaylist.UsePlaylistBasedOnSthInf;
import com.player.playlistapplication.dto.MusicMapper;
import com.player.playlistapplication.dto.PlaylistMapper;
import com.player.playlistapplication.repository.InfMusicRepository;
import com.player.playlistapplication.repository.InfPlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 * Handle all {@link PlaylistController} constructor parameters.
 * </p>
 */
@Component
public class PlaylistBuilder {
    private final InfPlaylistRepository playlistRepository;
    private final PlaylistMapper playlistMapper;
    private final InfMusicRepository musicRepository;
    private final MusicMapper musicMapper;
    private final UsePlaylistBasedOnSthInf usePlaylistBasedOnSthInf;

    private PlaylistBuilder(Builder builder) {
        this.playlistRepository = builder.playlistRepository;
        this.playlistMapper = builder.playlistMapper;
        this.musicRepository = builder.musicRepository;
        this.musicMapper = builder.musicMapper;
        this.usePlaylistBasedOnSthInf = builder.usePlaylistBasedOnSthInf;
    }

    public InfPlaylistRepository getPlaylistRepository() {
        return playlistRepository;
    }

    public PlaylistMapper getPlaylistMapper() {
        return playlistMapper;
    }

    public InfMusicRepository getMusicRepository() {
        return musicRepository;
    }

    public MusicMapper getMusicMapper() {
        return musicMapper;
    }

    public UsePlaylistBasedOnSthInf getUsePlaylistBasedOnSthInf() {
        return usePlaylistBasedOnSthInf;
    }

    @Component
    public static class Builder {
        @Autowired
        private InfPlaylistRepository playlistRepository;

        @Autowired
        private PlaylistMapper playlistMapper;

        @Autowired
        private InfMusicRepository musicRepository;

        @Autowired
        private MusicMapper musicMapper;

        @Autowired
        private UsePlaylistBasedOnSthInf usePlaylistBasedOnSthInf;
    }
}

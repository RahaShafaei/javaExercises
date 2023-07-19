package com.player.playlistapplication.controller.builder;


import com.player.playlistapplication.controller.MusicController;
import com.player.playlistapplication.dto.MusicMapper;
import com.player.playlistapplication.dto.PlaylistMapper;
import com.player.playlistapplication.repository.InfGenreRepository;
import com.player.playlistapplication.repository.InfMusicRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 * Handle all {@link MusicController} constructor parameters.
 * </p>
 */
@Getter
@Component
public class MusicBuilder {

    private final InfMusicRepository musicRepository;
    private final InfGenreRepository genreRepository;
    private final MusicMapper musicMapper;
    private final PlaylistMapper playlistMapper;

    private MusicBuilder(Builder builder) {
        this.musicRepository = builder.musicRepository;
        this.genreRepository = builder.genreRepository;
        this.musicMapper = builder.musicMapper;
        this.playlistMapper = builder.playlistMapper;
    }

    @Component
    public static class Builder {
        @Autowired
        private InfMusicRepository musicRepository;

        @Autowired
        private InfGenreRepository genreRepository;

        @Autowired
        private MusicMapper musicMapper;

        @Autowired
        private PlaylistMapper playlistMapper;
    }

}

package com.player.playlistapplication.controller.builder;

import com.player.playlistapplication.controller.GenreController;
import com.player.playlistapplication.dto.GenreMapper;
import com.player.playlistapplication.dto.MusicMapper;
import com.player.playlistapplication.repository.InfGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 * Handle all {@link GenreController} constructor parameters.
 * </p>
 */
@Component
public class GenreBuilder {
    private final InfGenreRepository repository;
    private final GenreMapper genreMapper;
    private final MusicMapper musicMapper;

    private GenreBuilder(Builder builder) {
        this.repository = builder.repository;
        this.genreMapper = builder.genreMapper;
        this.musicMapper = builder.musicMapper;
    }

    public InfGenreRepository getRepository() {
        return repository;
    }

    public GenreMapper getGenreMapper() {
        return genreMapper;
    }

    public MusicMapper getMusicMapper() {
        return musicMapper;
    }

    @Component
    public static class Builder {
        @Autowired
        private InfGenreRepository repository;

        @Autowired
        private GenreMapper genreMapper;

        @Autowired
        private MusicMapper musicMapper;
    }
}

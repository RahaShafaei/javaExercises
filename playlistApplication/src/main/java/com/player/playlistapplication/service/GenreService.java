package com.player.playlistapplication.service;

import com.player.playlistapplication.dto.GenreDto;
import com.player.playlistapplication.dto.MusicDto;
import com.player.playlistapplication.model.Genre;

import java.util.List;

public interface GenreService {
    public List<GenreDto> getGenres();

    public GenreDto getGenre(Long id);

    public List<MusicDto> getMusicsOfGenre(Long id);

    public Boolean deleteGenre(Long id);

    public GenreDto createGenre(Genre genre);
}

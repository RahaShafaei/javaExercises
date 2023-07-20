package com.player.playlistapplication.service;

import com.player.playlistapplication.controller.builder.GenreBuilder;
import com.player.playlistapplication.dto.GenreDto;
import com.player.playlistapplication.dto.GenreMapper;
import com.player.playlistapplication.dto.MusicDto;
import com.player.playlistapplication.dto.MusicMapper;
import com.player.playlistapplication.exception.ItemNotFoundException;
import com.player.playlistapplication.model.Genre;
import com.player.playlistapplication.repository.InfGenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("genreService")
public class GenreServiceImp implements GenreService {
    private final InfGenreRepository repository;
    private final GenreMapper genreMapper;
    private final MusicMapper musicMapper;

    public GenreServiceImp(GenreBuilder genreBuilder) {
        this.repository = genreBuilder.getRepository();
        this.genreMapper = genreBuilder.getGenreMapper();
        this.musicMapper = genreBuilder.getMusicMapper();
    }

    @Override
    public List<GenreDto> getGenres() {
        return repository.findAll()
                .stream()
                .map(genreMapper::toDto)
                .toList();
    }

    @Override
    public GenreDto getGenre(Long id) {
        Optional<Genre> genre = repository.findById(id);

        if (genre.isEmpty())
            throw new ItemNotFoundException("Genre id: " + id);

        return genreMapper.toDto(genre.get());
    }

    @Override
    public List<MusicDto> getMusicsOfGenre(Long id) {
        Optional<Genre> genre = repository.findById(id);

        if (genre.isEmpty())
            throw new ItemNotFoundException("Genre id: " + id);

        return genre.get()
                .getMusicList()
                .stream()
                .map(musicMapper::toDto)
                .toList();
    }

    @Override
    public Boolean deleteGenre(Long id) {
        Optional<Genre> genre = repository.findById(id);

        if (!genre.isEmpty()) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public GenreDto createGenre(Genre genre) {
        Genre savedGenre = repository.save(genre);
        return genreMapper.toDto(savedGenre);
    }
}

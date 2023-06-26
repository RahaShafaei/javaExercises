package com.player.playlistapplication.controller;

import com.player.playlistapplication.dto.GenreDto;
import com.player.playlistapplication.dto.GenreMapper;
import com.player.playlistapplication.model.Genre;
import com.player.playlistapplication.repository.InfGenreRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class GenreController {
    private InfGenreRepository repository;
    private GenreMapper genreMapper;

    public GenreController(InfGenreRepository repository,
                           GenreMapper genreMapper) {
        this.repository = repository;
        this.genreMapper = genreMapper;
    }

    @GetMapping("/genres")
    public List<GenreDto> retrieveAllGenres() {
        return repository.findAll()
                .stream()
                .map(genreMapper::toDto)
                .collect(toList());
    }

    @GetMapping("/genres/{id}")
    public GenreDto retrieveGenres(@PathVariable Long id) {
        return genreMapper.toDto(repository.findById(id).get());
    }
}

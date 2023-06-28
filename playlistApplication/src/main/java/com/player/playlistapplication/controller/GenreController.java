package com.player.playlistapplication.controller;

import com.player.playlistapplication.dto.GenreDto;
import com.player.playlistapplication.dto.GenreMapper;
import com.player.playlistapplication.dto.MusicDto;
import com.player.playlistapplication.dto.MusicMapper;
import com.player.playlistapplication.exception.ItemNotFoundException;
import com.player.playlistapplication.model.Genre;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.repository.InfGenreRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
public class GenreController {
    private InfGenreRepository repository;
    private GenreMapper genreMapper;
    private MusicMapper musicMapper;

    public GenreController(InfGenreRepository repository,
                           GenreMapper genreMapper,
                           MusicMapper musicMapper) {
        this.repository = repository;
        this.genreMapper = genreMapper;
        this.musicMapper = musicMapper;
    }

    @GetMapping("/genres")
    public List<GenreDto> retrieveAllGenres() {
        return repository.findAll()
                .stream()
                .map(genreMapper::toDto)
                .collect(toList());
    }

    @GetMapping("/genres/{id}")
    public GenreDto retrieveGenre(@PathVariable Long id) {
        return genreMapper.toDto(repository.findById(id).get());
    }

    @GetMapping("/genres/origin/{id}")
    public Genre retrieveOriginGenre(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    @GetMapping("/genres/{id}/musics")
    public List<MusicDto> retrieveMusicsOfGenre(@PathVariable Long id) {
        Optional<Genre> genre = repository.findById(id);

        if (genre.isEmpty())
            throw new ItemNotFoundException("id: " + id);

        return genre.get()
                .getMusicList()
                .stream()
                .map(musicMapper::toDto)
                .collect(toList());
    }

    @DeleteMapping("/genres/{id}")
    public void deleteGenre(@PathVariable Long id) {
        Optional<Genre> genre = repository.findById(id);

        if (genre.isEmpty())
            throw new ItemNotFoundException("id: " + id);

        repository.deleteById(id);
    }

    @PostMapping("/genres")
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        Genre savedGenre = repository.save(genre);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedGenre.getGenreId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}

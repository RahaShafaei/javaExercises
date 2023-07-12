package com.player.playlistapplication.controller;

import com.player.playlistapplication.dto.GenreDto;
import com.player.playlistapplication.dto.GenreMapper;
import com.player.playlistapplication.dto.MusicDto;
import com.player.playlistapplication.dto.MusicMapper;
import com.player.playlistapplication.exception.ItemNotFoundException;
import com.player.playlistapplication.model.Genre;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.repository.InfGenreRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 * Handle all {@link Genre} interactions.
 * </p>
 */
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

    /**
     * <p>Find all {@link Genre}s,convert them to {@link GenreDto} and return them.</p>
     *
     * @return list of {@link GenreDto}
     */
    @GetMapping("/genres")
    public List<GenreDto> retrieveAllGenres() {
        return repository.findAll()
                .stream()
                .map(genreMapper::toDto)
                .toList();
    }

    /**
     * <p>
     * Find a specific {@link Genre} according to taken id,
     * convert it to {@link GenreDto} and return it.
     * </p>
     *
     * @return A {@link GenreDto}
     */
    @GetMapping("/genres/{id}")
    public GenreDto retrieveGenre(@PathVariable Long id) {
        Optional<Genre> genre = repository.findById(id);

        if (genre.isEmpty())
            throw new ItemNotFoundException("Genre id: " + id);

        return genreMapper.toDto(genre.get());
    }

    /**
     * <p>
     * Find a specific {@link Genre} according to taken id,
     * get all of its {@link Music}, convert them to {@link MusicDto} and return them.
     * </p>
     *
     * @return list of {@link MusicDto}
     */
    @GetMapping("/genres/{id}/musics")
    public List<MusicDto> retrieveMusicsOfGenre(@PathVariable Long id) {
        Optional<Genre> genre = repository.findById(id);

        if (genre.isEmpty())
            throw new ItemNotFoundException("Genre id: " + id);

        return genre.get()
                .getMusicList()
                .stream()
                .map(musicMapper::toDto)
                .toList();
    }

    /**
     * Find a specific {@link Genre} according to taken id and delete it.
     */
    @DeleteMapping("/genres/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        Optional<Genre> genre = repository.findById(id);

        if (!genre.isEmpty()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * <p>Create a {@link Genre} </p>
     *
     * @return {@link ResponseEntity} of {@link Genre}
     */
    @PostMapping("/genres")
    public ResponseEntity<Genre> createGenre(@Valid @RequestBody Genre genre) {
        Genre savedGenre = repository.save(genre);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedGenre.getGenreId())
                .toUri();

        return ResponseEntity.created(location).body(savedGenre);
    }
}

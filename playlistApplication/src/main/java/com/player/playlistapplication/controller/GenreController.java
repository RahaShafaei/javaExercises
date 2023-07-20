package com.player.playlistapplication.controller;

import com.player.playlistapplication.dto.GenreDto;
import com.player.playlistapplication.dto.MusicDto;
import com.player.playlistapplication.model.Genre;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.service.GenreService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 * Handle all {@link Genre} interactions.
 * </p>
 */
@AllArgsConstructor
@RestController
public class GenreController {
    GenreService genreService;

    /**
     * <p>Find all {@link Genre}s,convert them to {@link GenreDto} and return them.</p>
     *
     * @return list of {@link GenreDto}
     */
    @GetMapping("/genres")
    public List<GenreDto> retrieveAllGenres() {
        return genreService.getGenres();
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
        return genreService.getGenre(id);
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
        return genreService.getMusicsOfGenre(id);
    }

    /**
     * Find a specific {@link Genre} according to taken id and delete it.
     */
    @DeleteMapping("/genres/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        boolean result = genreService.deleteGenre(id);

        if (result) {
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
    public ResponseEntity<GenreDto> createGenre(@Valid @RequestBody Genre genre) {
        GenreDto savedGenre = genreService.createGenre(genre);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedGenre.getGenreId())
                .toUri();

        return ResponseEntity.created(location).body(savedGenre);
    }
}

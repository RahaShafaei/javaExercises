package com.player.playlistapplication.controller;

import com.player.playlistapplication.controller.builder.MusicBuilder;
import com.player.playlistapplication.dto.MusicDto;
import com.player.playlistapplication.dto.MusicMapper;
import com.player.playlistapplication.dto.PlaylistDto;
import com.player.playlistapplication.dto.PlaylistMapper;
import com.player.playlistapplication.exception.ItemNotFoundException;
import com.player.playlistapplication.model.Genre;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.model.Playlist;
import com.player.playlistapplication.repository.InfGenreRepository;
import com.player.playlistapplication.repository.InfMusicRepository;
import com.player.playlistapplication.service.MusicService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 * Handle all {@link Music} interactions.
 * </p>
 */
@AllArgsConstructor
@RestController
public class MusicController {
    MusicService musicService;
    /**
     * <p>Find all {@link Music}s,convert them to {@link MusicDto} and return them.</p>
     *
     * @return list of {@link MusicDto}
     */
    @GetMapping("/musics")
    public List<MusicDto> retrieveAllMusic() {
        return musicService.getMusics();
    }

    /**
     * <p>
     * Find a specific {@link Music} according to taken id,
     * convert it to {@link MusicDto} and return it.
     * </p>
     *
     * @return A {@link MusicDto}
     */
    @GetMapping("/musics/{id}")
    public MusicDto retrieveMusic(@PathVariable Long id) {
        return musicService.getMusic(id);
    }

    /**
     * <p>
     * Find a specific {@link Music} according to taken id,
     * get all of its {@link Playlist}, convert them to {@link PlaylistDto} and return them.
     * </p>
     *
     * @return list of {@link PlaylistDto}
     */
    @GetMapping("/musics/{id}/playlists")
    public List<PlaylistDto> retrievePlaylistsOfMusic(@PathVariable Long id) {
        return musicService.getPlaylistsOfMusic(id);
    }

    /**
     * Find a specific {@link Music} according to taken id and delete it.
     */
    @DeleteMapping("/musics/{id}")
    public ResponseEntity<Void> deleteMusic(@PathVariable Long id) {
        boolean result = musicService.deleteMusic(id);

        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * <p>Create a {@link Music} by use of taken existing {@link Genre} entity id.</p>
     *
     * @return {@link EntityModel} of {@link Music}
     */
    @PostMapping("/musics/genre/{id}")
    public ResponseEntity<EntityModel<MusicDto>> createMusicOfGenre(
            @PathVariable Long id,
            @Valid @RequestBody Music music
    ) {
        MusicDto savedMusic = musicService.createMusicOfGenre(id, music);

        EntityModel<MusicDto> entityModel = EntityModel.of(savedMusic);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveMusic(savedMusic.getMusicId()));
        entityModel.add(link.withRel("music_link"));

        return ResponseEntity.created(link.toUri()).body(entityModel);
    }

}

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
import jakarta.validation.Valid;
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
@RestController
public class MusicController {
    private static final String MUSIC_ID = "Music id: ";
    private final InfMusicRepository musicRepository;
    private final InfGenreRepository genreRepository;
    private final MusicMapper musicMapper;
    private final PlaylistMapper playlistMapper;

    public MusicController(MusicBuilder musicBuilder) {
        this.musicRepository = musicBuilder.getMusicRepository();
        this.genreRepository = musicBuilder.getGenreRepository();
        this.musicMapper = musicBuilder.getMusicMapper();
        this.playlistMapper = musicBuilder.getPlaylistMapper();
    }

    /**
     * <p>Find all {@link Music}s,convert them to {@link MusicDto} and return them.</p>
     *
     * @return list of {@link MusicDto}
     */
    @GetMapping("/musics")
    public List<MusicDto> retrieveAllMusic() {
        return musicRepository.findAll()
                .stream()
                .map(musicMapper::toDto)
                .toList();
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
        Optional<Music> music = musicRepository.findById(id);

        if (music.isEmpty())
            throw new ItemNotFoundException(MUSIC_ID + id);

        return musicMapper.toDto(music.get());
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
        Optional<Music> music = musicRepository.findById(id);

        if (music.isEmpty())
            throw new ItemNotFoundException(MUSIC_ID + id);

        return music.get()
                .getPlaylists()
                .stream()
                .map(playlistMapper::toDto)
                .toList();
    }

    /**
     * Find a specific {@link Music} according to taken id and delete it.
     */
    @DeleteMapping("/musics/{id}")
    public void deleteMusic(@PathVariable Long id) {
        Optional<Music> music = musicRepository.findById(id);

        if (music.isEmpty())
            throw new ItemNotFoundException(MUSIC_ID + id);

        musicRepository.deleteById(id);
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
        Optional<Genre> genre = genreRepository.findById(id);

        if (genre.isEmpty())
            throw new ItemNotFoundException("Genre id: " + id);

        music.setGenre(genre.get());
        Music savedMusic = musicRepository.save(music);

        EntityModel<MusicDto> entityModel = EntityModel.of(musicMapper.toDto(savedMusic));

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveMusic(savedMusic.getMusicId()));
        entityModel.add(link.withRel("music_link"));

        return ResponseEntity.created(link.toUri()).body(entityModel);
    }

}

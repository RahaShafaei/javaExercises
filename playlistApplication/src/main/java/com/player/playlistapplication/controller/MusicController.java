package com.player.playlistapplication.controller;

import com.player.playlistapplication.dto.MusicDto;
import com.player.playlistapplication.dto.MusicMapper;
import com.player.playlistapplication.dto.PlaylistDto;
import com.player.playlistapplication.dto.PlaylistMapper;
import com.player.playlistapplication.exception.ItemNotFoundException;
import com.player.playlistapplication.model.Genre;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.repository.InfGenreRepository;
import com.player.playlistapplication.repository.InfMusicRepository;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class MusicController {
    private InfMusicRepository musicRepository;
    private InfGenreRepository genreRepository;
    private MusicMapper musicMapper;
    private PlaylistMapper playlistMapper;

    public MusicController(InfMusicRepository musicRepository,
                           MusicMapper musicMapper,
                           InfGenreRepository genreRepository,
                           PlaylistMapper playlistMapper) {
        this.musicRepository = musicRepository;
        this.genreRepository = genreRepository;
        this.musicMapper = musicMapper;
        this.playlistMapper = playlistMapper;
    }

    @GetMapping("/musics")
    public List<MusicDto> retrieveAllMusic() {
        return musicRepository.findAll()
                .stream()
                .map(musicMapper::toDto)
                .collect(toList());
    }

    @GetMapping("/musics/{id}")
    public MusicDto retrieveMusic(@PathVariable Long id) {
        return musicMapper.toDto(musicRepository.findById(id).get());
    }

    @GetMapping("/musics/{id}/playlists")
    public List<PlaylistDto> retrievePlaylistsOfMusic(@PathVariable Long id) {
        Optional<Music> music = musicRepository.findById(id);

        if (music.isEmpty())
            throw new ItemNotFoundException("id: " + id);

        return music.get()
                .getPlaylist()
                .stream()
                .map(playlistMapper::toDto)
                .collect(toList());
    }

    @DeleteMapping("/musics/{id}")
    public void deleteMusic(@PathVariable Long id) {
        Optional<Music> music = musicRepository.findById(id);

        if (music.isEmpty())
            throw new ItemNotFoundException("id: " + id);

        musicRepository.deleteById(id);
    }

    @PostMapping("/musics/genre/{id}")
    public EntityModel<Music> createMusicOfGenre(@PathVariable Long id , @RequestBody Music music) {
        Optional<Genre> genre = genreRepository.findById(id);

        if (genre.isEmpty())
            throw new ItemNotFoundException("id: " + id);

        music.setGenre(genre.get());
        Music savedMusic = musicRepository.save(music);

        EntityModel<Music> entityModel = EntityModel.of(savedMusic);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveMusic(savedMusic.getMusicId()));
        entityModel.add(link.withRel("all-musics"));

        return entityModel;
    }

}

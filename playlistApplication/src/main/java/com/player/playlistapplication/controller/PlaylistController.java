package com.player.playlistapplication.controller;

import com.player.playlistapplication.dto.MusicDto;
import com.player.playlistapplication.dto.MusicMapper;
import com.player.playlistapplication.dto.PlaylistDto;
import com.player.playlistapplication.dto.PlaylistMapper;
import com.player.playlistapplication.exception.ItemExistException;
import com.player.playlistapplication.exception.ItemNotFoundException;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.model.Playlist;
import com.player.playlistapplication.repository.InfMusicRepository;
import com.player.playlistapplication.repository.InfPlaylistRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PlaylistController {
    private InfPlaylistRepository playlistRepository;
    private PlaylistMapper playlistMapper;
    private InfMusicRepository musicRepository;
    private MusicMapper musicMapper;

    public PlaylistController(InfPlaylistRepository playlistRepository,
                              PlaylistMapper playlistMapper,
                              InfMusicRepository musicRepository,
                              MusicMapper musicMapper) {
        this.playlistRepository = playlistRepository;
        this.playlistMapper = playlistMapper;
        this.musicRepository = musicRepository;
        this.musicMapper = musicMapper;
    }

    @GetMapping("/playlists")
    public List<PlaylistDto> retrieveAllPlaylists() {
        return playlistRepository.findAll()
                .stream()
                .map(playlistMapper::toDto)
                .collect(toList());
    }

    @GetMapping("/playlists/{id}")
    public PlaylistDto retrievePlaylist(@PathVariable Long id) {
        return playlistMapper.toDto(playlistRepository.findById(id).get());
    }

    @GetMapping("/playlists/{id}/musics")
    public List<MusicDto> retrieveMusicsOfPlaylist(@PathVariable Long id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);

        if (playlist.isEmpty())
            throw new ItemNotFoundException("id: " + id);

        return playlist.get()
                .getMusicList()
                .stream()
                .map(musicMapper::toDto)
                .collect(toList());
    }

    @DeleteMapping("/playlists/{id}")
    public void deletePlaylist(@PathVariable Long id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);

        if (playlist.isEmpty())
            throw new ItemNotFoundException("id: " + id);

        playlistRepository.deleteById(id);
    }

    @PostMapping("/playlists")
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist) {
        Playlist savedPlaylist = playlistRepository.save(playlist);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPlaylist.getPlaylistId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/playlists/{playlistID}/music/{musicId}/add")
    public EntityModel<Playlist> addMusicToPlaylist(@PathVariable Long playlistID,
                                                       @PathVariable Long musicId) {

        Optional<Playlist> playlist = playlistRepository.findById(playlistID);
        Optional<Music> music = musicRepository.findById(musicId);

        if (playlist.get().getMusicList().contains(music.get())){
            throw new ItemExistException("musicId: " + musicId);
        }

        playlist.get().getMusicList().add(music.get());

        Playlist savedPlaylist = playlistRepository.save(playlist.get());

        EntityModel<Playlist> entityModel = EntityModel.of(savedPlaylist);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrievePlaylist(playlistID));
        entityModel.add(link.withRel("all-musics"));

        return entityModel;
    }

    @PostMapping("/playlists/{playlistID}/music/{musicId}/remove")
    public EntityModel<Playlist> removeMusicToPlaylist(@PathVariable Long playlistID,
                                                       @PathVariable Long musicId) {

        Optional<Playlist> playlist = playlistRepository.findById(playlistID);
        Optional<Music> music = musicRepository.findById(musicId);

        if (!playlist.get().getMusicList().contains(music.get())){
            throw new ItemNotFoundException("musicId: " + musicId);
        }

        playlist.get().getMusicList().remove(music.get());

        Playlist savedPlaylist = playlistRepository.save(playlist.get());

        EntityModel<Playlist> entityModel = EntityModel.of(savedPlaylist);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrievePlaylist(playlistID));
        entityModel.add(link.withRel("all-musics"));

        return entityModel;
    }
}

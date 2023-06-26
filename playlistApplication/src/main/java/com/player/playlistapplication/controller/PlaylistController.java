package com.player.playlistapplication.controller;

import com.player.playlistapplication.dto.PlaylistDto;
import com.player.playlistapplication.dto.PlaylistMapper;
import com.player.playlistapplication.repository.InfPlaylistRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class PlaylistController {
    private InfPlaylistRepository repository;
    private PlaylistMapper playlistMapper;

    public PlaylistController(InfPlaylistRepository repository, PlaylistMapper playlistMapper) {
        this.repository = repository;
        this.playlistMapper = playlistMapper;
    }

    @GetMapping("/playlists")
    public List<PlaylistDto> retrieveAllGenres() {
        return repository.findAll()
                .stream()
                .map(playlistMapper::toDto)
                .collect(toList());
    }

    @GetMapping("/playlists/{id}")
    public PlaylistDto retrieveGenres(@PathVariable Long id) {
        return playlistMapper.toDto(repository.findById(id).get());
    }
}

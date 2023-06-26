package com.player.playlistapplication.controller;

import com.player.playlistapplication.dto.MusicDto;
import com.player.playlistapplication.dto.MusicMapper;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.repository.InfMusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
public class MusicController {
    private InfMusicRepository repository;
    private MusicMapper musicMapper;

    public MusicController(InfMusicRepository repository,
                           MusicMapper musicMapper) {
        this.repository = repository;
        this.musicMapper = musicMapper;
    }

    @GetMapping("/musics")
    public List<MusicDto> retrieveAllMusic() {
        return repository.findAll()
                .stream()
                .map(musicMapper::toDto)
                .collect(toList());
    }

    @GetMapping("/musics/{id}")
    public MusicDto retrieveMusic(@PathVariable Long id) {
        return musicMapper.toDto(repository.findById(id).get());
    }
}

package com.player.playlistapplication.service;

import com.player.playlistapplication.dto.MusicDto;
import com.player.playlistapplication.dto.PlaylistDto;
import com.player.playlistapplication.model.Music;

import java.util.List;

public interface MusicService {

    public List<MusicDto> getMusics();

    public MusicDto getMusic(Long id);

    public List<PlaylistDto> getPlaylistsOfMusic(Long id);

    public Boolean deleteMusic(Long id);

    public MusicDto createMusicOfGenre(Long id, Music music);
}

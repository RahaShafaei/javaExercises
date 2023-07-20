package com.player.playlistapplication.service;

import com.player.playlistapplication.controller.builder.MusicBuilder;
import com.player.playlistapplication.dto.MusicDto;
import com.player.playlistapplication.dto.MusicMapper;
import com.player.playlistapplication.dto.PlaylistDto;
import com.player.playlistapplication.dto.PlaylistMapper;
import com.player.playlistapplication.exception.ItemNotFoundException;
import com.player.playlistapplication.model.Genre;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.repository.InfGenreRepository;
import com.player.playlistapplication.repository.InfMusicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("musicService")
public class MusicServiceImp implements MusicService {
    private static final String MUSIC_ID = "Music id: ";
    private final InfMusicRepository musicRepository;
    private final InfGenreRepository genreRepository;
    private final MusicMapper musicMapper;
    private final PlaylistMapper playlistMapper;

    public MusicServiceImp(MusicBuilder musicBuilder) {
        this.musicRepository = musicBuilder.getMusicRepository();
        this.genreRepository = musicBuilder.getGenreRepository();
        this.musicMapper = musicBuilder.getMusicMapper();
        this.playlistMapper = musicBuilder.getPlaylistMapper();
    }

    @Override
    public List<MusicDto> getMusics() {
        return musicRepository.findAll()
                .stream()
                .map(musicMapper::toDto)
                .toList();
    }

    @Override
    public MusicDto getMusic(Long id) {
        Optional<Music> music = musicRepository.findById(id);

        if (music.isEmpty())
            throw new ItemNotFoundException(MUSIC_ID + id);

        return musicMapper.toDto(music.get());
    }

    @Override
    public List<PlaylistDto> getPlaylistsOfMusic(Long id) {
        Optional<Music> music = musicRepository.findById(id);

        if (music.isEmpty())
            throw new ItemNotFoundException(MUSIC_ID + id);

        return music.get()
                .getPlaylists()
                .stream()
                .map(playlistMapper::toDto)
                .toList();
    }

    @Override
    public Boolean deleteMusic(Long id) {
        Optional<Music> music = musicRepository.findById(id);

        if (!music.isEmpty()) {
            musicRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public MusicDto createMusicOfGenre(Long id, Music music) {
        Optional<Genre> genre = genreRepository.findById(id);

        if (genre.isEmpty())
            throw new ItemNotFoundException("Genre id: " + id);

        music.setGenre(genre.get());

        return musicMapper.toDto(musicRepository.save(music));
    }
}

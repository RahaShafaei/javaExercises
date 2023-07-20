package com.player.playlistapplication.service;

import com.player.playlistapplication.controller.builder.PlaylistBuilder;
import com.player.playlistapplication.controller.smartPlaylist.InfFactoryPlaylistBasedOnSth;
import com.player.playlistapplication.controller.smartPlaylist.PlaylistBasedOnSth;
import com.player.playlistapplication.controller.smartPlaylist.UsePlaylistBasedOnSthInf;
import com.player.playlistapplication.dto.MusicDto;
import com.player.playlistapplication.dto.MusicMapper;
import com.player.playlistapplication.dto.PlaylistDto;
import com.player.playlistapplication.dto.PlaylistMapper;
import com.player.playlistapplication.exception.ItemExistException;
import com.player.playlistapplication.exception.ItemNotFoundException;
import com.player.playlistapplication.helper.EnmBasedOnSth;
import com.player.playlistapplication.helper.EntryBean;
import com.player.playlistapplication.model.Genre;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.model.Playlist;
import com.player.playlistapplication.repository.InfMusicRepository;
import com.player.playlistapplication.repository.InfPlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service("playlistService")
public class PlaylistServiceImp implements PlaylistService {
    private final InfPlaylistRepository playlistRepository;
    private final PlaylistMapper playlistMapper;
    private final InfMusicRepository musicRepository;
    private final MusicMapper musicMapper;
    private final UsePlaylistBasedOnSthInf usePlaylistBasedOnSthInf;

    public PlaylistServiceImp(PlaylistBuilder playlistBuilder) {
        this.playlistRepository = playlistBuilder.getPlaylistRepository();
        this.playlistMapper = playlistBuilder.getPlaylistMapper();
        this.musicRepository = playlistBuilder.getMusicRepository();
        this.musicMapper = playlistBuilder.getMusicMapper();
        this.usePlaylistBasedOnSthInf = playlistBuilder.getUsePlaylistBasedOnSthInf();
    }

    @Override
    public List<PlaylistDto> getPlaylists() {
        return playlistRepository.findAll()
                .stream()
                .map(playlistMapper::toDto)
                .toList();
    }

    @Override
    public PlaylistDto getPlaylist(Long id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);

        if (playlist.isEmpty())
            throw new ItemNotFoundException("id: " + id);

        return playlistMapper.toDto(playlist.get());
    }

    @Override
    public List<MusicDto> getMusicsOfPlaylist(Long id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);

        if (playlist.isEmpty())
            throw new ItemNotFoundException("id: " + id);

        return playlist.get()
                .getMusicList()
                .stream()
                .map(musicMapper::toDto)
                .toList();
    }

    @Override
    public Boolean deletePlaylist(Long id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);

        if (!playlist.isEmpty()) {
            playlistRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public PlaylistDto createPlaylist(Playlist playlist) {
        return playlistMapper.toDto(playlistRepository.save(playlist));
    }

    @Override
    public PlaylistDto addMusicToPlaylist(Long playlistID, Long musicId) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistID);
        Optional<Music> music = musicRepository.findById(musicId);

        if (playlist.get().getMusicList().contains(music.get())) {
            throw new ItemExistException("musicId: " + musicId);
        }

        playlist.get().getMusicList().add(music.get());

        return playlistMapper.toDto(playlistRepository.save(playlist.get()));
    }

    @Override
    public PlaylistDto removeMusicToPlaylist(Long playlistID, Long musicId) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistID);
        Optional<Music> music = musicRepository.findById(musicId);

        if (!playlist.get().getMusicList().contains(music.get())) {
            throw new ItemNotFoundException("musicId: " + musicId);
        }

        playlist.get().getMusicList().remove(music.get());

        return playlistMapper.toDto(playlistRepository.save(playlist.get()));
    }

    @Override
    public List<PlaylistDto> getAllPlaylistOfSmartType(String smartType, String name) {
        InfFactoryPlaylistBasedOnSth creation = usePlaylistBasedOnSthInf;
        usePlaylistBasedOnSthInf.setEntryBean(new EntryBean(name));

        List<Playlist> playlists = Stream.of(EnmBasedOnSth.valueOf(smartType.toUpperCase()))
                .map(creation::create)
                .map(PlaylistBasedOnSth::findPlaylistBasedOnSth)
                .flatMap(Collection::stream)
                .toList();

        return playlists
                .stream()
                .map(playlistMapper::toDto)
                .toList();
    }

    @Override
    public PlaylistDto createSmartPlaylist(String smartType, EntryBean entryBean) {
        InfFactoryPlaylistBasedOnSth creation = usePlaylistBasedOnSthInf;
        usePlaylistBasedOnSthInf.setEntryBean(entryBean);

        List<Music> musicList = Stream.of(EnmBasedOnSth.valueOf(smartType.toUpperCase()))
                .map(creation::create)
                .map(PlaylistBasedOnSth::collectingMusic)
                .flatMap(Collection::stream)
                .toList();

        Playlist playlist = new Playlist();
        playlist.setName(entryBean.getPlayListName());
        playlist.setMusicList(musicList);

        return playlistMapper.toDto(playlistRepository.save(playlist));
    }
}

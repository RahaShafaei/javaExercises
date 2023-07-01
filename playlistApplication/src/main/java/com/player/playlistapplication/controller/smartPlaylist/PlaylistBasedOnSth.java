package com.player.playlistapplication.controller.smartPlaylist;

import com.player.playlistapplication.helper.EntryBean;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.model.Playlist;
import com.player.playlistapplication.repository.InfGenreRepository;
import com.player.playlistapplication.repository.InfMusicRepository;
import com.player.playlistapplication.repository.InfPlaylistRepository;

import java.util.List;

public abstract class PlaylistBasedOnSth {
    protected EntryBean entryBean;

    protected InfMusicRepository musicRepository;

    protected InfGenreRepository genreRepository;

    protected InfPlaylistRepository playlistRepository;

    public PlaylistBasedOnSth(EntryBean entryBean) {
        this.entryBean = entryBean;
    }

    public void setMusicRepository(InfMusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public void setGenreRepository(InfGenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public void setPlaylistRepository(InfPlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public abstract List<Playlist> findPlaylistBasedOnSth();
    public abstract List<Music> collectingMusic();
}

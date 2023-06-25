package com.player.playlistapplication.controller;

import com.player.playlistapplication.repository.Music;
import com.player.playlistapplication.repository.Playlist;

import java.util.List;

public class PlaylistBasedOnGenre extends PlaylistBasedOnSth {

    public PlaylistBasedOnGenre(Integer fromYear, Integer toYear, String name) {
        super(fromYear, toYear, name);
    }

    @Override
    public void collectingMusic() {

    }

    @Override
    public List<Playlist> findPlaylistBasedOnSth() {
        return null;
    }
}

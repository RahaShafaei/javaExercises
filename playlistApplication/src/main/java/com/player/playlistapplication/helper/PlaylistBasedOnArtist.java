package com.player.playlistapplication.helper;

import com.player.playlistapplication.model.Playlist;

import java.util.List;

public class PlaylistBasedOnArtist extends PlaylistBasedOnSth {

    public PlaylistBasedOnArtist(Integer fromYear, Integer toYear, String name) {
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
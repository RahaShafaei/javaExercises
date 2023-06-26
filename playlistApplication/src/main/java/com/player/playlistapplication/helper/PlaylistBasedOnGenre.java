package com.player.playlistapplication.helper;

import com.player.playlistapplication.model.Playlist;

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

package com.player.playlistapplication.helper;

import com.player.playlistapplication.model.Playlist;

import java.util.List;

public abstract class PlaylistBasedOnSth {
    protected Integer fromYear;
    protected Integer toYear;
    protected String name;
    protected List<Playlist> finedPlaylistBasedOnSth;

    public PlaylistBasedOnSth(Integer fromYear, Integer toYear, String name) {
        this.fromYear = fromYear;
        this.toYear = toYear;
        this.name = name;
    }

    public List<Playlist> getFinedPlaylistBasedOnSth() {
        return finedPlaylistBasedOnSth;
    }

    public abstract List<Playlist> findPlaylistBasedOnSth();
    public abstract void collectingMusic();
}

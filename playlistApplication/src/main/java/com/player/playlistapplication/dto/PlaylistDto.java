package com.player.playlistapplication.dto;

import java.util.Map;

public class PlaylistDto {
    private long playlistId;
    private String name;
    private Map<Long, String> musicList;

    public long getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(long playlistId) {
        this.playlistId = playlistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Long, String> getMusicList() {
        return musicList;
    }

    public void setMusicList(Map<Long, String> musicList) {
        this.musicList = musicList;
    }
}

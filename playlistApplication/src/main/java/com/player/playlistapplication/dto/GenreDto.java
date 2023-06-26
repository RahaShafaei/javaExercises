package com.player.playlistapplication.dto;

import java.util.Map;

public class GenreDto {
    private long genreId;

    private String name;

    private Map<Long, String> musicList;

    public long getGenreId() {
        return genreId;
    }

    public void setGenreId(long genreId) {
        this.genreId = genreId;
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

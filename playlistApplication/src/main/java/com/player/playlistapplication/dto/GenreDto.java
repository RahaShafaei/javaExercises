package com.player.playlistapplication.dto;

import java.util.Map;
/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 *     Because return {@link com.player.playlistapplication.model.Genre} as a response object
 *     caused "StackOverflowError", this class created to handle response objects
 *     for {@link com.player.playlistapplication.model.Genre}.
 * </p>
 */
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

    @Override
    public String toString() {
        return "GenreDto{" +
                "genreId=" + genreId +
                ", name='" + name + '\'' +
                ", musicList=" + musicList +
                '}';
    }
}

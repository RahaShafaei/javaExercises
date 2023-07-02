package com.player.playlistapplication.dto;

import java.util.Map;
/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 *     Because return {@link com.player.playlistapplication.model.Music} as a response object
 *     caused "StackOverflowError", this class created to handle response objects
 *     for {@link com.player.playlistapplication.model.Music}.
 * </p>
 */
public class MusicDto {
    private long musicId;
    private String name;
    private String artist;
    private Integer pubYear;
    private Map<Long, String> playlists;
    private Map<Long, String> genre;

    public long getMusicId() {
        return musicId;
    }

    public void setMusicId(long musicId) {
        this.musicId = musicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Integer getPubYear() {
        return pubYear;
    }

    public void setPubYear(Integer pubYear) {
        this.pubYear = pubYear;
    }

    public Map<Long, String> getGenre() {
        return genre;
    }

    public void setGenre(Map<Long, String> genre) {
        this.genre = genre;
    }

    public Map<Long, String> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Map<Long, String> playlists) {
        this.playlists = playlists;
    }
}

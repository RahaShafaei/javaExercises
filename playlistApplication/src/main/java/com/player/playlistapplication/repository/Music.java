package com.player.playlistapplication.repository;

import jakarta.persistence.*;

@Entity
public class Music {
    @Id
    @GeneratedValue
    private long musicId;
    private String name;
    private String artist;
    private Integer pubYear;

    @ManyToOne
//            (fetch = FetchType.LAZY)
//    @JoinColumn(name="playlistId", nullable=false)
    private Playlist playlist;

    public Music() {
    }

    public long getMusicId() {
        return musicId;
    }

    public void setMusicId(long id) {
        this.musicId = id;
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

    public void setPubYear(Integer year) {
        this.pubYear = year;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    @Override
    public String toString() {
        return "Music{" +
                "musicId=" + musicId +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", pubYear=" + pubYear +
                ", playlist=" + playlist +
                '}';
    }
}

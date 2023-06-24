package com.player.playlistapplication.repository;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Music {
    @Id
    @GeneratedValue
    private long musicId;
    private String name;
    private String artist;
    private Integer pubYear;

    @ManyToMany
    @JoinTable(
            name = "music_playlist",
            joinColumns = @JoinColumn(name = "musicId"),
            inverseJoinColumns = @JoinColumn(name = "playlistId"))
    private Set<Playlist> playlists;

    @ManyToOne
    private Genre generalist;

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

    public Set<Playlist> getPlaylist() {
        return playlists;
    }

    public void setPlaylist(Set<Playlist> playlist) {
        this.playlists = playlist;
    }

    public Genre getGeneralist() {
        return generalist;
    }

    public void setGeneralist(Genre generalist) {
        this.generalist = generalist;
    }

    @Override
    public String toString() {
        return "Music{" +
                "musicId=" + musicId +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", pubYear=" + pubYear +
                ", playlists=" + playlists +
                ", generalist=" + generalist +
                '}';
    }
}

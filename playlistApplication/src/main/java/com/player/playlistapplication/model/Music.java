package com.player.playlistapplication.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "musicId")
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
    private List<Playlist> playlists;

    @ManyToOne
    private Genre genre;

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

    public List<Playlist> getPlaylist() {
        return playlists;
    }

    public void setPlaylist(List<Playlist> playlist) {
        this.playlists = playlist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre generalist) {
        this.genre = generalist;
    }

    @Override
    public String toString() {
        return "Music{" +
                "musicId=" + musicId +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", pubYear=" + pubYear +
                ", playlists=" + playlists +
                ", genre=" + genre +
                '}';
    }
}

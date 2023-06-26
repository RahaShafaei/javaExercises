package com.player.playlistapplication.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "playlistId")
@Entity
public class Playlist {

    @Id
    @GeneratedValue
    private long playlistId;
    private String name;
    @ManyToMany(mappedBy = "playlists")
    private List<Music> musicList;

    public Playlist() {
    }

    public long getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(long id) {
        this.playlistId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Music> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<Music> musicSet) {
        this.musicList = musicSet;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlistId=" + playlistId +
                ", name='" + name + '\'' +
                ", musicSet=" + musicList +
                '}';
    }
}

package com.player.playlistapplication.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Playlist {

    @Id
    @GeneratedValue
    private long playlistId;
    private String name;
    @OneToMany(mappedBy="playlist")
    private Set<Music> musicSet;

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

    public Set<Music> getMusicSet() {
        return musicSet;
    }

    public void setMusicSet(Set<Music> musicSet) {
        this.musicSet = musicSet;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlistId=" + playlistId +
                ", name='" + name + '\'' +
                ", musicSet=" + musicSet +
                '}';
    }
}

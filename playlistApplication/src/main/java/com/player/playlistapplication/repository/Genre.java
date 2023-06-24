package com.player.playlistapplication.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Genre {

    @Id
    @GeneratedValue
    private long genreId;

    private String name;

    @OneToMany(mappedBy="generalist")
    private Set<Music> musicSet;

    public Genre() {
    }
}

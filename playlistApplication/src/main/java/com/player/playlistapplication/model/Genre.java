package com.player.playlistapplication.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "genreId")
@Entity
public class Genre {

    @Id
    @GeneratedValue(generator = "genre_sequence-generator")
    @GenericGenerator(
            name = "genre_sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "genre_sequence"),
                    @Parameter(name = "initial_value", value = "8"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long genreId;

    private String name;

    @OneToMany(mappedBy="genre")
    private List<Music> musicList;

    public Genre() {
    }

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

    public List<Music> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<Music> musicSet) {
        this.musicList = musicSet;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genreId=" + genreId +
                ", name='" + name + '\'' +
                ", musicSet=" + musicList +
                '}';
    }
}

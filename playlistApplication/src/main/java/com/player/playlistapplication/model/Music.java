package com.player.playlistapplication.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.List;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "musicId")
@Entity
public class Music {
    @Id
    @GeneratedValue(generator = "music_sequence-generator")
    @GenericGenerator(
            name = "music_sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "music_sequence"),
                    @Parameter(name = "initial_value", value = "23"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long musicId;

    @Size(min = 2, message = "name should have at least 2 character")
    private String name;

    @Size(min = 2, message = "artist should have at least 2 character")
    private String artist;

    @Digits(message = "pubYear should be a four-digit number", integer = 4, fraction = 0)
    private Integer pubYear;

    @ManyToMany(mappedBy = "musicList")
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

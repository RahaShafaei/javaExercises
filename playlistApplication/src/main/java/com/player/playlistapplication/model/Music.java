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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.List;
@NoArgsConstructor
@Getter
@Setter
@ToString
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

    @Size(min = 2, message = "{music.name.min}")
    private String name;

    @Size(min = 2, message = "{music.artist.min}")
    private String artist;

    @Digits(message = "{music.pubYear.digits}", integer = 4, fraction = 0)
    private Integer pubYear;

    @ManyToMany(mappedBy = "musicList")
    private List<Playlist> playlists;

    @ManyToOne
    private Genre genre;

}

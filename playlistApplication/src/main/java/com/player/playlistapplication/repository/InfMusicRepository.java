package com.player.playlistapplication.repository;

import com.player.playlistapplication.model.Genre;
import com.player.playlistapplication.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfMusicRepository extends JpaRepository<Music, Long> {
    List<Music> findAllByNameAndPubYearBetween(
            String name,
            Integer pubYearStart,
            Integer pubYearEnd
    );

    List<Music> findAllByGenreAndPubYearBetween(
            Genre genre,
            Integer fromYear,
            Integer toYear
    );
}

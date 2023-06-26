package com.player.playlistapplication.dto;

import com.player.playlistapplication.helper.Helper;
import com.player.playlistapplication.model.Genre;
import com.player.playlistapplication.model.Music;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GenreMapper {

    public GenreDto toDto(Genre genre) {
        GenreDto genreDto = new GenreDto();

        genreDto.setGenreId(genre.getGenreId());
        genreDto.setName(genre.getName());

        Map<Long, String> musicDtl = Helper.convertListToMap(
                genre.getMusicList(),
                Music::getMusicId,
                Music::getName
        );
        genreDto.setMusicList(musicDtl);

        return genreDto;
    }
}

package com.player.playlistapplication.dto;

import com.player.playlistapplication.helper.Helper;
import com.player.playlistapplication.model.Genre;
import com.player.playlistapplication.model.Music;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 *     To convert {@link Genre} to {@link GenreDto}.
 * </p>
 */
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

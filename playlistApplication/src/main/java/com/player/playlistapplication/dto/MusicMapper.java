package com.player.playlistapplication.dto;

import com.player.playlistapplication.helper.ConvertListToMap;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.model.Playlist;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 * To convert {@link Music} to {@link MusicDto}.
 * </p>
 */
@Component
public class MusicMapper {

    public MusicDto toDto(Music music) {
        MusicDto musicDto = new MusicDto();

        musicDto.setMusicId(music.getMusicId());
        musicDto.setName(music.getName());
        musicDto.setArtist(music.getArtist());
        musicDto.setPubYear(music.getPubYear());

        Map<Long, String> tempGenre = new HashMap<>();
        tempGenre.put(music.getGenre().getGenreId(), music.getGenre().getName());
        musicDto.setGenre(tempGenre);

        if (music.getPlaylists() != null) {
            Map<Long, String> playlistDtl = ConvertListToMap.apply(
                    music.getPlaylists(),
                    Playlist::getPlaylistId,
                    Playlist::getName
            );
            musicDto.setPlaylists(playlistDtl);
        }

        return musicDto;
    }
}

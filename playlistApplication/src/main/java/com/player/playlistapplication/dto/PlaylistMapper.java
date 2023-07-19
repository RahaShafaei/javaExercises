package com.player.playlistapplication.dto;

import com.player.playlistapplication.helper.ConvertListToMap;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.model.Playlist;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 * To convert {@link Playlist} to {@link PlaylistDto}.
 * </p>
 */
@Component
public class PlaylistMapper {

    public PlaylistDto toDto(Playlist playlist) {
        PlaylistDto playlistDto = new PlaylistDto();

        playlistDto.setPlaylistId(playlist.getPlaylistId());
        playlistDto.setName(playlist.getName());

        if (playlist.getMusicList() != null) {
            Map<Long, String> musicDtl = ConvertListToMap.apply(
                    playlist.getMusicList(),
                    Music::getMusicId,
                    Music::getName
            );
            playlistDto.setMusicList(musicDtl);
        }

        return playlistDto;
    }
}

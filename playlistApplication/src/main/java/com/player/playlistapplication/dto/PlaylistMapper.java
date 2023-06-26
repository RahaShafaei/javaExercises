package com.player.playlistapplication.dto;

import com.player.playlistapplication.helper.Helper;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.model.Playlist;
import com.player.playlistapplication.model.Playlist;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PlaylistMapper {
    
    public PlaylistDto toDto(Playlist playlist) {
        PlaylistDto playlistDto = new PlaylistDto();

        playlistDto.setPlaylistId(playlist.getPlaylistId());
        playlistDto.setName(playlist.getName());
        Map<Long, String> musicDtl = Helper.convertListToMap(
                playlist.getMusicList(),
                Music::getMusicId,
                Music::getName
        );
        playlistDto.setMusicList(musicDtl);

        return playlistDto;
    }
}

package com.player.playlistapplication.service;

import com.player.playlistapplication.dto.MusicDto;
import com.player.playlistapplication.dto.PlaylistDto;
import com.player.playlistapplication.helper.EntryBean;
import com.player.playlistapplication.model.Playlist;

import java.util.List;

public interface PlaylistService {

    public List<PlaylistDto> getPlaylists();

    public PlaylistDto getPlaylist(Long id);

    public List<MusicDto> getMusicsOfPlaylist(Long id);

    public Boolean deletePlaylist(Long id);

    public PlaylistDto createPlaylist(Playlist playlist);

    public PlaylistDto addMusicToPlaylist(Long playlistID, Long musicId);

    public PlaylistDto removeMusicToPlaylist(Long playlistID, Long musicId);

    public List<PlaylistDto> getAllPlaylistOfSmartType(String smartType, String name);

    public PlaylistDto createSmartPlaylist(String smartType, EntryBean entryBean);

}

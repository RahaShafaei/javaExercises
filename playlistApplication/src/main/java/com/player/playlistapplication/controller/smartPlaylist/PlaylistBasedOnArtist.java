package com.player.playlistapplication.controller.smartPlaylist;

import com.player.playlistapplication.helper.EntryBean;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.model.Playlist;

import java.util.List;

public class PlaylistBasedOnArtist extends PlaylistBasedOnSth {

    public PlaylistBasedOnArtist(EntryBean entryBean) {
        super(entryBean);
    }

    @Override
    public List<Music> collectingMusic() {
        List<Music> musicList = musicRepository.findAllByNameAndPubYearBetween(
                entryBean.getName(),
                entryBean.getFromYear(),
                entryBean.getToYear()
        );

        return musicList;
    }

    @Override
    public List<Playlist> findPlaylistBasedOnSth() {
        List<Playlist> playlists = playlistRepository.findByMusicList_Artist(entryBean.getName());

        return playlists;
    }
}
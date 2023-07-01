package com.player.playlistapplication.controller.smartPlaylist;

import com.player.playlistapplication.helper.EntryBean;
import com.player.playlistapplication.model.Genre;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.model.Playlist;

import java.util.List;

public class PlaylistBasedOnGenre extends PlaylistBasedOnSth {

    public PlaylistBasedOnGenre(EntryBean entryBean) {
        super(entryBean);
    }

    @Override
    public List<Music> collectingMusic() {
        Genre genre = genreRepository.findByName(entryBean.getName());

        List<Music> musicList = musicRepository.findAllByGenreAndPubYearBetween(
                genre,
                entryBean.getFromYear(),
                entryBean.getToYear()
        );

        return musicList;
    }

    @Override
    public List<Playlist> findPlaylistBasedOnSth() {
        Genre genre = genreRepository.findByName(entryBean.getName());

        List<Playlist> playlists = playlistRepository.findAllByMusicList_Genre(genre);

        return playlists;
    }
}

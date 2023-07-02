package com.player.playlistapplication.controller.smartPlaylist;

import com.player.playlistapplication.controller.adjustments.Player;
import com.player.playlistapplication.helper.EntryBean;
import com.player.playlistapplication.model.Genre;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.model.Playlist;

import java.util.List;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 *     This is one of {@link PlaylistBasedOnSth} subclasses to implement FactoryMethod Pattern's
 *     settings for {@link Genre}s.
 * </p>
 */
public class PlaylistBasedOnGenre extends PlaylistBasedOnSth {

    public PlaylistBasedOnGenre(EntryBean entryBean) {
        super(entryBean);
    }

    /**
     * <p>
     *     Collecting list of {@link Music} according {@link Music}'s "Genre".
     * </p>
     * @return List of {@link Music}
     */
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

    /**
     * <p>
     *     Find list of {@link Playlist} according to {@link Music}'s "Genre".
     * </p>
     * @return List of {@link Playlist}
     */
    @Override
    public List<Playlist> findPlaylistBasedOnSth() {
        Genre genre = genreRepository.findByName(entryBean.getName());

        List<Playlist> playlists = playlistRepository.findAllByMusicList_Genre(genre);

        return playlists;
    }
}

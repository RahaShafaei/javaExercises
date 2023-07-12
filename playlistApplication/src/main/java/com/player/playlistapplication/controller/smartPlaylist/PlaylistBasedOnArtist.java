package com.player.playlistapplication.controller.smartPlaylist;

import com.player.playlistapplication.helper.EntryBean;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.model.Playlist;

import java.util.List;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 * This is one of {@link PlaylistBasedOnSth} subclasses to implement FactoryMethod Pattern's
 * settings for Artists.
 * </p>
 */

public class PlaylistBasedOnArtist extends PlaylistBasedOnSth {

    public PlaylistBasedOnArtist(EntryBean entryBean) {
        super(entryBean);
    }

    /**
     * <p>
     * Collecting list of {@link Music} according {@link Music}'s "Artist".
     * </p>
     *
     * @return List of {@link Music}
     */
    @Override
    public List<Music> collectingMusic() {
        return musicRepository.findAllByArtistAndPubYearBetween(
                entryBean.getName(),
                entryBean.getFromYear(),
                entryBean.getToYear()
        );
    }


    /**
     * <p>
     * Find list of {@link Playlist} according to {@link Music}'s "Artist".
     * </p>
     *
     * @return List of {@link Playlist}
     */
    @Override
    public List<Playlist> findPlaylistBasedOnSth() {
        return playlistRepository.findByMusicList_Artist(entryBean.getName());
    }
}
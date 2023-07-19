package com.player.playlistapplication.controller.smartPlaylist;

import com.player.playlistapplication.helper.EntryBean;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.model.Playlist;
import com.player.playlistapplication.repository.InfGenreRepository;
import com.player.playlistapplication.repository.InfMusicRepository;
import com.player.playlistapplication.repository.InfPlaylistRepository;
import lombok.Setter;

import java.util.List;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 * This is the main class of FactoryMethod Pattern to find list
 * of {@link Playlist} or list of {@link Music} according
 * to {@link Music}'s "Artist" or {@link Music}'s "Genre".
 * </p>
 * <p></p>
 * <p>
 * Because it was necessary to get an instance of this class, it was not possible
 * to add a Spring annotation for this class to use IOC.
 * </p>
 */
@Setter
public abstract class PlaylistBasedOnSth {
    protected EntryBean entryBean;

    protected InfMusicRepository musicRepository;

    protected InfGenreRepository genreRepository;

    protected InfPlaylistRepository playlistRepository;

    public PlaylistBasedOnSth(EntryBean entryBean) {
        this.entryBean = entryBean;
    }

    /**
     * <p>
     * Find list of {@link Playlist} according to {@link Music}'s "Artist" or {@link Music}'s "Genre".
     * </p>
     *
     * @return List of {@link Playlist}
     */
    public abstract List<Playlist> findPlaylistBasedOnSth();

    /**
     * <p>
     * Collecting list of {@link Music} according to {@link Music}'s "Artist" or {@link Music}'s "Genre".
     * </p>
     *
     * @return List of {@link Music}
     */
    public abstract List<Music> collectingMusic();
}

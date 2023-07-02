package com.player.playlistapplication.controller.smartPlaylist;

import com.player.playlistapplication.helper.EnmBasedOnSth;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.model.Playlist;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 *     This is the main interface for implementing FactoryMethod Pattern to
 *     create {@link PlaylistBasedOnSth} subclasses.
 * </p>
 */
public interface InfFactoryPlaylistBasedOnSth {
    PlaylistBasedOnSth create(EnmBasedOnSth type);
}

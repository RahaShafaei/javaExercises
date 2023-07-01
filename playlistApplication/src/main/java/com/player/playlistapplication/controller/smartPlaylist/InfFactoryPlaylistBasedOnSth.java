package com.player.playlistapplication.controller.smartPlaylist;

import com.player.playlistapplication.helper.EnmBasedOnSth;

public interface InfFactoryPlaylistBasedOnSth {
    PlaylistBasedOnSth create(EnmBasedOnSth type);
}

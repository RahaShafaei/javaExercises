package com.player.playlistapplication.controller.smartPlaylist;

import com.player.playlistapplication.helper.EnmPlaylistBasedOnSth;
import org.springframework.stereotype.Component;

public interface InfFactoryPlaylistBasedOnSth {
    PlaylistBasedOnSth create(EnmPlaylistBasedOnSth type);
}

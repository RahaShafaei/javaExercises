package com.player.playlistapplication.controller;

import com.player.playlistapplication.helper.PlaylistBasedOnSthEnum;

public interface FactoryPlaylistBasedOnSth {
    PlaylistBasedOnSth create(PlaylistBasedOnSthEnum type);
}

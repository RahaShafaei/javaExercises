package com.player.playlistapplication.controller;

import com.player.playlistapplication.helper.MusicSpeed;
import com.player.playlistapplication.helper.PlaybackState;
import com.player.playlistapplication.helper.PlaybackType;
import com.player.playlistapplication.helper.Volume;

import java.util.ArrayList;
import java.util.List;

public class PlayingAdjustments {
    private MusicSpeed musicSpeed;
    private PlaybackState playbackState;
    private PlaybackType playbackType;
    private Volume volume;
    final List<Player> players;

    public PlayingAdjustments() {
        this.musicSpeed = MusicSpeed.NORMAL;
        this.playbackState = PlaybackState.PLAY;
        this.playbackType = PlaybackType.NORMAL;
        this.volume = Volume.MEDIUM;
        players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }


    void adjusting(MusicSpeed musicSpeed,
                   PlaybackState playbackState,
                   PlaybackType playbackType,
                   Volume volume) {
        this.musicSpeed = musicSpeed;
        this.playbackState = playbackState;
        this.playbackType = playbackType;
        this.volume = volume;
        this.notifyMusicians();
    }

    private void notifyMusicians() {
        for (var mus : players) {
            mus.setMusicSpeed(this.musicSpeed);
            mus.setPlaybackState(this.playbackState);
            mus.setPlaybackType(this.playbackType);
            mus.setVolume(this.volume);
            mus.playing();
        }
    }
}

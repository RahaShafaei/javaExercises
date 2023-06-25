package com.player.playlistapplication.controller;

import com.player.playlistapplication.helper.MusicSpeedEnum;
import com.player.playlistapplication.helper.PlaybackStateEnum;
import com.player.playlistapplication.helper.PlaybackTypeEnum;
import com.player.playlistapplication.helper.VolumeEnum;

import java.util.ArrayList;
import java.util.List;

public class PlayingAdjustments {
    private MusicSpeedEnum musicSpeedEnum;
    private PlaybackStateEnum playbackStateEnum;
    private PlaybackTypeEnum playbackTypeEnum;
    private VolumeEnum volumeEnum;
    final List<Player> players;

    public PlayingAdjustments() {
        this.musicSpeedEnum = MusicSpeedEnum.NORMAL;
        this.playbackStateEnum = PlaybackStateEnum.PLAY;
        this.playbackTypeEnum = PlaybackTypeEnum.NORMAL;
        this.volumeEnum = VolumeEnum.MEDIUM;
        players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }


    public void playingByDefaultAdjustments() {
        for (var mus : players) {
            mus.setMusicSpeed(this.musicSpeedEnum);
            mus.setPlaybackState(this.playbackStateEnum);
            mus.setPlaybackType(this.playbackTypeEnum);
            mus.setVolume(this.volumeEnum);
            mus.playing();
        }
    }

    public void changeAdjustmentsAndPlaying(MusicSpeedEnum musicSpeedEnum,
                                            PlaybackStateEnum playbackStateEnum,
                                            PlaybackTypeEnum playbackTypeEnum,
                                            VolumeEnum volumeEnum) {
        this.musicSpeedEnum = musicSpeedEnum;
        this.playbackStateEnum = playbackStateEnum;
        this.playbackTypeEnum = playbackTypeEnum;
        this.volumeEnum = volumeEnum;
        this.notifyMusicians();
    }

    private void notifyMusicians() {
        for (var mus : players) {
            mus.setMusicSpeed(this.musicSpeedEnum);
            mus.setPlaybackState(this.playbackStateEnum);
            mus.setPlaybackType(this.playbackTypeEnum);
            mus.setVolume(this.volumeEnum);
            mus.playing();
        }
    }
}

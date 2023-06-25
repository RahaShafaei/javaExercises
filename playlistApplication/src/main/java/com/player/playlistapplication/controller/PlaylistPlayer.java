package com.player.playlistapplication.controller;

import com.player.playlistapplication.helper.*;

public class PlaylistPlayer extends Player {

    @Override
    public void setMusicSpeed(MusicSpeedEnum musicSpeedEnum) {
        super.musicSpeedEnum = musicSpeedEnum;
    }

    @Override
    public void setPlaybackState(PlaybackStateEnum playbackStateEnum) {
        super.playbackStateEnum = playbackStateEnum;
    }

    @Override
    public void setPlaybackType(PlaybackTypeEnum playbackTypeEnum) {
        super.playbackTypeEnum = playbackTypeEnum;
    }

    @Override
    public void setVolume(VolumeEnum volumeEnum) {
        super.volumeEnum = volumeEnum;
    }

    @Override
    public void playVerySlow() {
        System.out.println(String.format("Playlist playing %s.",super.musicSpeedEnum));
    }

    @Override
    public void playSlow() {
        System.out.println(String.format("Playlist playing %s.",super.musicSpeedEnum));
    }

    @Override
    public void playNormal() {
        System.out.println(String.format("Playlist playing %s.",super.musicSpeedEnum));
    }

    @Override
    public void playFast() {
        System.out.println(String.format("Playlist playing %s.",super.musicSpeedEnum));
    }

    @Override
    public void playVeryFast() {
        System.out.println(String.format("Playlist playing %s.",super.musicSpeedEnum));
    }

    @Override
    public void play() {
        System.out.println(String.format("Playlist %sed.",super.playbackStateEnum));
    }

    @Override
    public void pause() {
        System.out.println(String.format("Playlist %sed.",super.playbackStateEnum));
    }

    @Override
    public void stop() {
        System.out.println(String.format("Playlist %sed.",super.playbackStateEnum));
    }

    @Override
    public void next() {
        System.out.println(String.format("%s playlist playing.",super.playbackStateEnum));
    }

    @Override
    public void previous() {
        System.out.println(String.format("%s playlist playing.",super.playbackStateEnum));
    }

    @Override
    public void playShuffleType() {
        System.out.println(String.format("Playlists played in %s type.",super.playbackTypeEnum));
    }

    @Override
    public void repeatOneType() {
        System.out.println(String.format("Playlists played in %s type.",super.playbackTypeEnum));
    }

    @Override
    public void repeatAllType() {
        System.out.println(String.format("Playlists played in %s type.",super.playbackTypeEnum));
    }

    @Override
    public void normalType() {
        System.out.println(String.format("Playlists played in %s type.",super.playbackTypeEnum));
    }

    @Override
    public void increaseVolume() {
        System.out.println(String.format("Playlist volumeEnum %sed.",super.volumeEnum));
    }

    @Override
    public void decreaseVolume() {
        System.out.println(String.format("Playlist volumeEnum %sed.",super.volumeEnum));
    }

    @Override
    public void mediumVolume() {
        System.out.println(String.format("Playlist volumeEnum set on %s.",super.volumeEnum));
    }
}

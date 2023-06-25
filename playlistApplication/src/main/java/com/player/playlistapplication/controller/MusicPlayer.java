package com.player.playlistapplication.controller;

import com.player.playlistapplication.helper.MusicSpeedEnum;
import com.player.playlistapplication.helper.PlaybackStateEnum;
import com.player.playlistapplication.helper.PlaybackTypeEnum;
import com.player.playlistapplication.helper.VolumeEnum;

public class MusicPlayer extends Player {

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
        System.out.println(String.format("Music playing %s.",super.musicSpeedEnum));
    }

    @Override
    public void playSlow() {
        System.out.println(String.format("Music playing %s.",super.musicSpeedEnum));
    }

    @Override
    public void playNormal() {
        System.out.println(String.format("Music playing %s.",super.musicSpeedEnum));
    }

    @Override
    public void playFast() {
        System.out.println(String.format("Music playing %s.",super.musicSpeedEnum));
    }

    @Override
    public void playVeryFast() {
        System.out.println(String.format("Music playing %s.",super.musicSpeedEnum));
    }

    @Override
    public void play() {
        System.out.println(String.format("Music %sed.",super.playbackStateEnum));
    }

    @Override
    public void pause() {
        System.out.println(String.format("Music %sed.",super.playbackStateEnum));
    }

    @Override
    public void stop() {
        System.out.println(String.format("Music %sed.",super.playbackStateEnum));
    }

    @Override
    public void next() {
        System.out.println(String.format("%s music playing.",super.playbackStateEnum));
    }

    @Override
    public void previous() {
        System.out.println(String.format("%s music playing.",super.playbackStateEnum));
    }

    @Override
    public void playShuffleType() {
        System.out.println(String.format("Musics played in %s type.",super.playbackTypeEnum));
    }

    @Override
    public void repeatOneType() {
        System.out.println(String.format("Musics played in %s type.",super.playbackTypeEnum));
    }

    @Override
    public void repeatAllType() {
        System.out.println(String.format("Musics played in %s type.",super.playbackTypeEnum));
    }

    @Override
    public void normalType() {
        System.out.println(String.format("Musics played in %s type.",super.playbackTypeEnum));
    }

    @Override
    public void increaseVolume() {
        System.out.println(String.format("Music volumeEnum %sed.",super.volumeEnum));
    }

    @Override
    public void decreaseVolume() {
        System.out.println(String.format("Music volumeEnum %sed.",super.volumeEnum));
    }

    @Override
    public void mediumVolume() {
        System.out.println(String.format("Music volumeEnum set on %s.",super.volumeEnum));
    }
}

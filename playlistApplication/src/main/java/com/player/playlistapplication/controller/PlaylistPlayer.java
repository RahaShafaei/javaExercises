package com.player.playlistapplication.controller;

import com.player.playlistapplication.helper.*;

public class PlaylistPlayer extends Player {

    @Override
    public void setMusicSpeed(MusicSpeed musicSpeed) {
        super.musicSpeed = musicSpeed;
    }

    @Override
    public void setPlaybackState(PlaybackState playbackState) {
        super.playbackState = playbackState;
    }

    @Override
    public void setPlaybackType(PlaybackType playbackType) {
        super.playbackType = playbackType;
    }

    @Override
    public void setVolume(Volume volume) {
        super.volume = volume;
    }

    @Override
    public void playVerySlow() {
        System.out.println(String.format("Playlist playing %s.",super.musicSpeed));
    }

    @Override
    public void playSlow() {
        System.out.println(String.format("Playlist playing %s.",super.musicSpeed));
    }

    @Override
    public void playNormal() {
        System.out.println(String.format("Playlist playing %s.",super.musicSpeed));
    }

    @Override
    public void playFast() {
        System.out.println(String.format("Playlist playing %s.",super.musicSpeed));
    }

    @Override
    public void playVeryFast() {
        System.out.println(String.format("Playlist playing %s.",super.musicSpeed));
    }

    @Override
    public void play() {
        System.out.println(String.format("Playlist %sed.",super.playbackState));
    }

    @Override
    public void pause() {
        System.out.println(String.format("Playlist %sed.",super.playbackState));
    }

    @Override
    public void stop() {
        System.out.println(String.format("Playlist %sed.",super.playbackState));
    }

    @Override
    public void next() {
        System.out.println(String.format("%s playlist playing.",super.playbackState));
    }

    @Override
    public void previous() {
        System.out.println(String.format("%s playlist playing.",super.playbackState));
    }

    @Override
    public void playShuffleType() {
        System.out.println(String.format("Playlists played in %s type.",super.playbackType));
    }

    @Override
    public void repeatOneType() {
        System.out.println(String.format("Playlists played in %s type.",super.playbackType));
    }

    @Override
    public void repeatAllType() {
        System.out.println(String.format("Playlists played in %s type.",super.playbackType));
    }

    @Override
    public void normalType() {
        System.out.println(String.format("Playlists played in %s type.",super.playbackType));
    }

    @Override
    public void increaseVolume() {
        System.out.println(String.format("Playlist volume %sed.",super.volume));
    }

    @Override
    public void decreaseVolume() {
        System.out.println(String.format("Playlist volume %sed.",super.volume));
    }

    @Override
    public void mediumVolume() {
        System.out.println(String.format("Playlist volume set on %s.",super.volume));
    }
}

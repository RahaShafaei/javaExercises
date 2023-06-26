package com.player.playlistapplication.helper;

public class MusicPlayer extends Player {

    @Override
    public void setMusicSpeed(EnmMusicSpeed enmMusicSpeed) {
        super.enmMusicSpeed = enmMusicSpeed;
    }

    @Override
    public void setPlaybackState(EnmPlaybackState enmPlaybackState) {
        super.enmPlaybackState = enmPlaybackState;
    }

    @Override
    public void setPlaybackType(EnmPlaybackType enmPlaybackType) {
        super.enmPlaybackType = enmPlaybackType;
    }

    @Override
    public void setVolume(EnmVolume enmVolume) {
        super.enmVolume = enmVolume;
    }

    @Override
    public void playVerySlow() {
        System.out.println(String.format("Music playing %s.",super.enmMusicSpeed));
    }

    @Override
    public void playSlow() {
        System.out.println(String.format("Music playing %s.",super.enmMusicSpeed));
    }

    @Override
    public void playNormal() {
        System.out.println(String.format("Music playing %s.",super.enmMusicSpeed));
    }

    @Override
    public void playFast() {
        System.out.println(String.format("Music playing %s.",super.enmMusicSpeed));
    }

    @Override
    public void playVeryFast() {
        System.out.println(String.format("Music playing %s.",super.enmMusicSpeed));
    }

    @Override
    public void play() {
        System.out.println(String.format("Music %sed.",super.enmPlaybackState));
    }

    @Override
    public void pause() {
        System.out.println(String.format("Music %sed.",super.enmPlaybackState));
    }

    @Override
    public void stop() {
        System.out.println(String.format("Music %sed.",super.enmPlaybackState));
    }

    @Override
    public void next() {
        System.out.println(String.format("%s music playing.",super.enmPlaybackState));
    }

    @Override
    public void previous() {
        System.out.println(String.format("%s music playing.",super.enmPlaybackState));
    }

    @Override
    public void playShuffleType() {
        System.out.println(String.format("Musics played in %s type.",super.enmPlaybackType));
    }

    @Override
    public void repeatOneType() {
        System.out.println(String.format("Musics played in %s type.",super.enmPlaybackType));
    }

    @Override
    public void repeatAllType() {
        System.out.println(String.format("Musics played in %s type.",super.enmPlaybackType));
    }

    @Override
    public void normalType() {
        System.out.println(String.format("Musics played in %s type.",super.enmPlaybackType));
    }

    @Override
    public void increaseVolume() {
        System.out.println(String.format("Music enmVolume %sed.",super.enmVolume));
    }

    @Override
    public void decreaseVolume() {
        System.out.println(String.format("Music enmVolume %sed.",super.enmVolume));
    }

    @Override
    public void mediumVolume() {
        System.out.println(String.format("Music enmVolume set on %s.",super.enmVolume));
    }
}

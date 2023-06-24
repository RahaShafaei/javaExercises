package com.player.playlistapplication.controller;

import com.player.playlistapplication.helper.MusicSpeed;
import com.player.playlistapplication.helper.PlaybackState;
import com.player.playlistapplication.helper.PlaybackType;
import com.player.playlistapplication.helper.Volume;

public abstract class Player {
    protected MusicSpeed musicSpeed;
    protected PlaybackState playbackState;
    protected PlaybackType playbackType;
    protected Volume volume;

    public abstract void setMusicSpeed(MusicSpeed musicSpeed);

    public abstract void setPlaybackState(PlaybackState playbackState);

    public abstract void setPlaybackType(PlaybackType playbackType);

    public abstract void setVolume(Volume volume);

    public void playing() {
        switch (this.musicSpeed) {
            case VERY_SLOW -> this.playVerySlow();
            case SLOW -> this.playSlow();
            case FAST -> this.playFast();
            case VERY_FAST -> this.playVeryFast();
            case NORMAL -> this.playNormal();
        }

        switch (this.playbackState) {
            case PAUSE -> this.pause();
            case STOP -> this.stop();
            case NEXT -> this.next();
            case PREVIOUS -> this.previous();
            case PLAY -> this.play();
        }

        switch (this.playbackType) {
            case SHUFFLE -> this.playShuffleType();
            case REPEAT_ONE -> this.repeatOneType();
            case REPEAT_ALL -> this.repeatAllType();
            case NORMAL -> this.normalType();
        }

        switch (this.volume) {
            case INCREASE -> this.increaseVolume();
            case DECREASE -> this.decreaseVolume();
            case MEDIUM -> this.mediumVolume();
        }
    }

    public abstract void playVerySlow();

    public abstract void playSlow();

    public abstract void playNormal();

    public abstract void playFast();

    public abstract void playVeryFast();

    public abstract void play();

    public abstract void pause();

    public abstract void stop();

    public abstract void next();

    public abstract void previous();

    public abstract void playShuffleType();

    public abstract void repeatOneType();

    public abstract void repeatAllType();
    public abstract void normalType();


    public abstract void increaseVolume();

    public abstract void decreaseVolume();

    public abstract void mediumVolume();
}

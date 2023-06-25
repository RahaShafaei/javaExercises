package com.player.playlistapplication.controller;

import com.player.playlistapplication.helper.MusicSpeedEnum;
import com.player.playlistapplication.helper.PlaybackStateEnum;
import com.player.playlistapplication.helper.PlaybackTypeEnum;
import com.player.playlistapplication.helper.VolumeEnum;

public abstract class Player {
    protected MusicSpeedEnum musicSpeedEnum;
    protected PlaybackStateEnum playbackStateEnum;
    protected PlaybackTypeEnum playbackTypeEnum;
    protected VolumeEnum volumeEnum;

    public abstract void setMusicSpeed(MusicSpeedEnum musicSpeedEnum);

    public abstract void setPlaybackState(PlaybackStateEnum playbackStateEnum);

    public abstract void setPlaybackType(PlaybackTypeEnum playbackTypeEnum);

    public abstract void setVolume(VolumeEnum volumeEnum);

    public void playing() {
        switch (this.musicSpeedEnum) {
            case VERY_SLOW -> this.playVerySlow();
            case SLOW -> this.playSlow();
            case FAST -> this.playFast();
            case VERY_FAST -> this.playVeryFast();
            case NORMAL -> this.playNormal();
        }

        switch (this.playbackStateEnum) {
            case PAUSE -> this.pause();
            case STOP -> this.stop();
            case NEXT -> this.next();
            case PREVIOUS -> this.previous();
            case PLAY -> this.play();
        }

        switch (this.playbackTypeEnum) {
            case SHUFFLE -> this.playShuffleType();
            case REPEAT_ONE -> this.repeatOneType();
            case REPEAT_ALL -> this.repeatAllType();
            case NORMAL -> this.normalType();
        }

        switch (this.volumeEnum) {
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

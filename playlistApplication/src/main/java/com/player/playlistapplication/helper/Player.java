package com.player.playlistapplication.helper;

public abstract class Player {
    protected EnmMusicSpeed enmMusicSpeed;
    protected EnmPlaybackState enmPlaybackState;
    protected EnmPlaybackType enmPlaybackType;
    protected EnmVolume enmVolume;

    public abstract void setMusicSpeed(EnmMusicSpeed enmMusicSpeed);

    public abstract void setPlaybackState(EnmPlaybackState enmPlaybackState);

    public abstract void setPlaybackType(EnmPlaybackType enmPlaybackType);

    public abstract void setVolume(EnmVolume enmVolume);

    public void playing() {
        switch (this.enmMusicSpeed) {
            case VERY_SLOW -> this.playVerySlow();
            case SLOW -> this.playSlow();
            case FAST -> this.playFast();
            case VERY_FAST -> this.playVeryFast();
            case NORMAL -> this.playNormal();
        }

        switch (this.enmPlaybackState) {
            case PAUSE -> this.pause();
            case STOP -> this.stop();
            case NEXT -> this.next();
            case PREVIOUS -> this.previous();
            case PLAY -> this.play();
        }

        switch (this.enmPlaybackType) {
            case SHUFFLE -> this.playShuffleType();
            case REPEAT_ONE -> this.repeatOneType();
            case REPEAT_ALL -> this.repeatAllType();
            case NORMAL -> this.normalType();
        }

        switch (this.enmVolume) {
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

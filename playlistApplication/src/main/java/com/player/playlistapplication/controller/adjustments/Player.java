package com.player.playlistapplication.controller.adjustments;

import com.player.playlistapplication.helper.EnmMusicSpeed;
import com.player.playlistapplication.helper.EnmPlaybackState;
import com.player.playlistapplication.helper.EnmPlaybackType;
import com.player.playlistapplication.helper.EnmVolume;
import com.player.playlistapplication.repository.InfMusicRepository;
import com.player.playlistapplication.repository.InfPlaylistRepository;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 *     This is the main class of Observer Pattern to apply desire adjustments.
 * </p>
 *
 * <p>
 *     Because it was necessary to get an instance of this class, it was not possible
 *     to add a Spring annotation for this class to use IOC.
 * </p>
 */
public abstract class Player {
    protected EnmMusicSpeed enmMusicSpeed;
    protected EnmPlaybackState enmPlaybackState;
    protected EnmPlaybackType enmPlaybackType;
    protected EnmVolume enmVolume;

    protected Long id;
    protected InfPlaylistRepository playlistRepository;
    protected InfMusicRepository musicRepository;

    public Player(Long id,
                  InfPlaylistRepository playlistRepository,
                  InfMusicRepository musicRepository) {
        this.id = id;
        this.playlistRepository = playlistRepository;
        this.musicRepository = musicRepository;
    }

    public abstract void setMusicSpeed(EnmMusicSpeed enmMusicSpeed);

    public abstract void setPlaybackState(EnmPlaybackState enmPlaybackState);

    public abstract void setPlaybackType(EnmPlaybackType enmPlaybackType);

    public abstract void setVolume(EnmVolume enmVolume);

    public InfPlaylistRepository getPlaylistRepository() {
        return playlistRepository;
    }

    public void setPlaylistRepository(InfPlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public InfMusicRepository getMusicRepository() {
        return musicRepository;
    }

    public void setMusicRepository(InfMusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    /**
     * It is the main method of Observer pattern to apply the taken settings.
     */
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

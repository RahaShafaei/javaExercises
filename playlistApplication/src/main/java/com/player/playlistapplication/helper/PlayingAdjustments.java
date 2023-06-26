package com.player.playlistapplication.helper;

import java.util.ArrayList;
import java.util.List;

public class PlayingAdjustments {
    private EnmMusicSpeed enmMusicSpeed;
    private EnmPlaybackState enmPlaybackState;
    private EnmPlaybackType enmPlaybackType;
    private EnmVolume enmVolume;
    final List<Player> players;

    public PlayingAdjustments() {
        this.enmMusicSpeed = EnmMusicSpeed.NORMAL;
        this.enmPlaybackState = EnmPlaybackState.PLAY;
        this.enmPlaybackType = EnmPlaybackType.NORMAL;
        this.enmVolume = EnmVolume.MEDIUM;
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
            mus.setMusicSpeed(this.enmMusicSpeed);
            mus.setPlaybackState(this.enmPlaybackState);
            mus.setPlaybackType(this.enmPlaybackType);
            mus.setVolume(this.enmVolume);
            mus.playing();
        }
    }

    public void changeAdjustmentsAndPlaying(EnmMusicSpeed enmMusicSpeed,
                                            EnmPlaybackState enmPlaybackState,
                                            EnmPlaybackType enmPlaybackType,
                                            EnmVolume enmVolume) {
        this.enmMusicSpeed = enmMusicSpeed;
        this.enmPlaybackState = enmPlaybackState;
        this.enmPlaybackType = enmPlaybackType;
        this.enmVolume = enmVolume;
        this.notifyMusicians();
    }

    private void notifyMusicians() {
        for (var mus : players) {
            mus.setMusicSpeed(this.enmMusicSpeed);
            mus.setPlaybackState(this.enmPlaybackState);
            mus.setPlaybackType(this.enmPlaybackType);
            mus.setVolume(this.enmVolume);
            mus.playing();
        }
    }
}

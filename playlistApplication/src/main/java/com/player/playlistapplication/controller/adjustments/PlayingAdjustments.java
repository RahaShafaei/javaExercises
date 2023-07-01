package com.player.playlistapplication.controller.adjustments;

import com.player.playlistapplication.helper.EnmMusicSpeed;
import com.player.playlistapplication.helper.EnmPlaybackState;
import com.player.playlistapplication.helper.EnmPlaybackType;
import com.player.playlistapplication.helper.EnmVolume;

import java.util.ArrayList;
import java.util.List;

public class PlayingAdjustments {
    final List<Player> players;
    private EnmMusicSpeed enmMusicSpeed;
    private EnmPlaybackState enmPlaybackState;
    private EnmPlaybackType enmPlaybackType;
    private EnmVolume enmVolume;

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

            System.out.println(mus);
        }
    }

    public void changeAdjustmentsAndPlaying(EnmMusicSpeed enmMusicSpeed,
                                            EnmPlaybackState enmPlaybackState,
                                            EnmPlaybackType enmPlaybackType,
                                            EnmVolume enmVolume) {

        this.enmMusicSpeed = enmMusicSpeed != null ? enmMusicSpeed : this.enmMusicSpeed;
        this.enmPlaybackState = enmPlaybackState != null ? enmPlaybackState : this.enmPlaybackState;
        this.enmPlaybackType = enmPlaybackType != null ? enmPlaybackType : this.enmPlaybackType;
        this.enmVolume = enmVolume != null ? enmVolume : this.enmVolume;

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

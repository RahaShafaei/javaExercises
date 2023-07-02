package com.player.playlistapplication.controller.adjustments;

import com.player.playlistapplication.helper.EnmMusicSpeed;
import com.player.playlistapplication.helper.EnmPlaybackState;
import com.player.playlistapplication.helper.EnmPlaybackType;
import com.player.playlistapplication.helper.EnmVolume;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 *      The Observer Pattern used to notify the playlist or music objects
 *      when there are changes or updates.The Observer pattern promotes loose coupling and
 *      allows objects to be notified of changes without being tightly coupled to each other.
 * </p>
 * <p></p>
 * <p>In the case of a playlist application, it's better to have a central manager
 * or controller that handles the playlist operations. Using the Singleton pattern
 * for this manager class ensures that there is only one instance, and it can be
 * accessed globally across the application.
 * </p>
 * <p></p>
 * <p>The Initialize-on-demand-holder idiom is a secure way of creating a lazy initialized singleton
 * object in Java.</p>
 * <p></p>
 * <p>The technique is as lazy as possible and works in all known versions of Java. It takes
 * advantage of language guarantees about class initialization, and will therefore work correctly
 * in all Java-compliant compilers and virtual machines.</p>
 * <p></p>
 * <p>The inner class is referenced no earlier (and therefore loaded no earlier by the class loader)
 * than the moment that getInstance() is called. Thus, this solution is thread-safe without
 * requiring special language constructs (i.e. volatile or synchronized).</p>
 */
public class PlayingAdjustments {
    final List<Player> players;
    private EnmMusicSpeed enmMusicSpeed;
    private EnmPlaybackState enmPlaybackState;
    private EnmPlaybackType enmPlaybackType;
    private EnmVolume enmVolume;

    private PlayingAdjustments() {

        this.enmMusicSpeed = EnmMusicSpeed.NORMAL;
        this.enmPlaybackState = EnmPlaybackState.PLAY;
        this.enmPlaybackType = EnmPlaybackType.NORMAL;
        this.enmVolume = EnmVolume.MEDIUM;

        players = new ArrayList<>();
    }

    /**
     * Singleton instance.
     *
     * @return Singleton instance
     */
    public static PlayingAdjustments getInstance() {
        return HelperHolder.INSTANCE;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    /**
     * <p>
     * Playing players according to default adjustments that do on the
     * constructor method by using the general playing() method defined
     * in {@link  com.player.playlistapplication.controller.adjustments.Player} class
     * </p>
     */
    public void playingByDefaultAdjustments() {
        for (var mus : players) {

            mus.setMusicSpeed(this.enmMusicSpeed);
            mus.setPlaybackState(this.enmPlaybackState);
            mus.setPlaybackType(this.enmPlaybackType);
            mus.setVolume(this.enmVolume);

            mus.playing();
        }
    }

    /**
     * @param enmMusicSpeed
     *        {@link EnmMusicSpeed}
     * @param enmPlaybackState
     *        {@link EnmPlaybackState}
     * @param enmPlaybackType
     *        {@link EnmPlaybackType}
     * @param enmVolume
     *        {@link EnmVolume}
     * <p>
     *
     * </p>
     * <p>
     * Playing players according to customize adjustments based on settings taken from outside
     * by using the general playing() method defined
     * in {@link  com.player.playlistapplication.controller.adjustments.Player} class
     * </p>
     */
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

    /**
     * It is defined to apply the taken settings.
     */
    private void notifyMusicians() {
        for (var mus : players) {

            mus.setMusicSpeed(this.enmMusicSpeed);
            mus.setPlaybackState(this.enmPlaybackState);
            mus.setPlaybackType(this.enmPlaybackType);
            mus.setVolume(this.enmVolume);

            mus.playing();
        }
    }

    /**
     * Provides the lazy-loaded Singleton instance.
     */
    private static class HelperHolder {
        private static final PlayingAdjustments INSTANCE = new PlayingAdjustments();
    }
}

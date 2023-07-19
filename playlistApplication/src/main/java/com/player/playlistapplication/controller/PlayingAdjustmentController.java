package com.player.playlistapplication.controller;

import com.player.playlistapplication.controller.adjustments.Player;
import com.player.playlistapplication.controller.adjustments.Playing;
import com.player.playlistapplication.controller.adjustments.PlayingAdjustments;
import com.player.playlistapplication.controller.adjustments.MusicPlayer;
import com.player.playlistapplication.controller.adjustments.PlaylistPlayer;
import com.player.playlistapplication.helper.*;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.model.Playlist;
import org.springframework.web.bind.annotation.*;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 * Handle all Adjustments.
 * </p>
 */
@RestController
public class PlayingAdjustmentController {
    private final Playing playing;

    public PlayingAdjustmentController(Playing playing) {
        this.playing = playing;
    }

    /**
     * <p>
     * Playing ByDefaultAdjustment of {@link MusicPlayer}
     * or {@link PlaylistPlayer}
     * of specific type({@link EnmBasedOnSth}.PLAYLIST or {@link EnmBasedOnSth}.MUSIC)
     * by getting an id of existing {@link Playlist} or {@link Music}.
     *
     * @param adjustmentType a string to specify type of playing.
     *                       should be on of these values {@link EnmBasedOnSth}.MUSIC or
     *                       {@link EnmBasedOnSth}.PLAYLIST.
     * @param id             name of existing {@link Music} or {@link Playlist} entity.
     */
    @GetMapping("/adjustment/{adjustmentType}/{id}")
    public void playingByDefaultAdjustment(@PathVariable String adjustmentType,
                                           @PathVariable Long id) {

        playing.setId(id);
        Player player = playing.create(EnmBasedOnSth.valueOf(adjustmentType.toUpperCase()));

        PlayingAdjustments playingAdjustments = PlayingAdjustments.getInstance();
        playingAdjustments.addPlayer(player);
        playingAdjustments.playingByDefaultAdjustments();
    }

    /**
     * <p>
     * Playing ByCustomizeAdjustment of {@link MusicPlayer}
     * or {@link PlaylistPlayer}
     * of specific type({@link EnmBasedOnSth}.PLAYLIST or {@link EnmBasedOnSth}.MUSIC)
     * by getting an id of existing {@link Playlist} or {@link Music} and getting {@link AdjustmentBean}.
     *
     * @param adjustmentType a string to specify type of playing.
     *                       should be on of these values {@link EnmBasedOnSth}.MUSIC or
     *                       {@link EnmBasedOnSth}.PLAYLIST.
     * @param id             name of existing {@link Music} or {@link Playlist} entity.
     * @param adjustmentBean Necessary adjustments for playing {@link Music} or {@link Playlist}.
     */
    @PostMapping("/adjustment/{adjustmentType}/{id}")
    public void playingByCustomizeAdjustment(@PathVariable String adjustmentType,
                                             @PathVariable Long id,
                                             @RequestBody AdjustmentBean adjustmentBean) {

        playing.setId(id);
        Player player = playing.create(EnmBasedOnSth.valueOf(adjustmentType.toUpperCase()));

        PlayingAdjustments playingAdjustments = PlayingAdjustments.getInstance();
        playingAdjustments.addPlayer(player);
        playingAdjustments.changeAdjustmentsAndPlaying(
                EnmMusicSpeed.valueOf(adjustmentBean.getEnmMusicSpeed().toUpperCase()),
                EnmPlaybackState.valueOf(adjustmentBean.getEnmPlaybackState().toUpperCase()),
                EnmPlaybackType.valueOf(adjustmentBean.getEnmPlaybackType().toUpperCase()),
                EnmVolume.valueOf(adjustmentBean.getEnmVolume().toUpperCase())
        );
    }
}

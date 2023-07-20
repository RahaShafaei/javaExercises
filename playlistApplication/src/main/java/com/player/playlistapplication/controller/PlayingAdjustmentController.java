package com.player.playlistapplication.controller;

import com.player.playlistapplication.controller.adjustments.MusicPlayer;
import com.player.playlistapplication.controller.adjustments.PlaylistPlayer;
import com.player.playlistapplication.helper.*;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.model.Playlist;
import com.player.playlistapplication.service.PlayingAdjustmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 * Handle all Adjustments.
 * </p>
 */
@AllArgsConstructor
@RestController
public class PlayingAdjustmentController {
    PlayingAdjustmentService playingAdjustmentService;
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
        playingAdjustmentService.playingByDefaultAdjustment(adjustmentType, id);
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
        playingAdjustmentService.playingByCustomizeAdjustment(adjustmentType, id, adjustmentBean);
    }
}

package com.player.playlistapplication.service;


import com.player.playlistapplication.controller.adjustments.Player;
import com.player.playlistapplication.controller.adjustments.Playing;
import com.player.playlistapplication.controller.adjustments.PlayingAdjustments;
import com.player.playlistapplication.helper.*;
import org.springframework.stereotype.Service;

@Service("playingAdjustmentService")
public class PlayingAdjustmentServiceImp implements PlayingAdjustmentService {

    private final Playing playing;

    public PlayingAdjustmentServiceImp(Playing playing) {
        this.playing = playing;
    }

    @Override
    public void playingByDefaultAdjustment(String adjustmentType, Long id) {
        playing.setId(id);
        Player player = playing.create(EnmBasedOnSth.valueOf(adjustmentType.toUpperCase()));

        PlayingAdjustments playingAdjustments = PlayingAdjustments.getInstance();
        playingAdjustments.addPlayer(player);
        playingAdjustments.playingByDefaultAdjustments();
    }

    @Override
    public void playingByCustomizeAdjustment(String adjustmentType, Long id, AdjustmentBean adjustmentBean) {
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

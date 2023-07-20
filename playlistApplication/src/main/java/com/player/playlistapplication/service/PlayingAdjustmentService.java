package com.player.playlistapplication.service;

import com.player.playlistapplication.helper.AdjustmentBean;

public interface PlayingAdjustmentService {

    public void playingByDefaultAdjustment(String adjustmentType, Long id);

    public void playingByCustomizeAdjustment(String adjustmentType, Long id, AdjustmentBean adjustmentBean);

}

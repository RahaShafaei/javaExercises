package com.player.playlistapplication.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 * To handle playing adjustments.
 * </p>
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdjustmentBean {
    private String enmMusicSpeed;
    private String enmPlaybackState;
    private String enmPlaybackType;
    private String enmVolume;
}

package com.player.playlistapplication.helper;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 *     To handle playing adjustments.
 * </p>
 */
public class AdjustmentBean {
    private String enmMusicSpeed;
    private String enmPlaybackState;
    private String enmPlaybackType;
    private String enmVolume;

    public AdjustmentBean(String enmMusicSpeed, String enmPlaybackState, String enmPlaybackType, String enmVolume) {
        this.enmMusicSpeed = enmMusicSpeed;
        this.enmPlaybackState = enmPlaybackState;
        this.enmPlaybackType = enmPlaybackType;
        this.enmVolume = enmVolume;
    }

    public String getEnmMusicSpeed() {
        return enmMusicSpeed;
    }

    public void setEnmMusicSpeed(String enmMusicSpeed) {
        this.enmMusicSpeed = enmMusicSpeed;
    }

    public String getEnmPlaybackState() {
        return enmPlaybackState;
    }

    public void setEnmPlaybackState(String enmPlaybackState) {
        this.enmPlaybackState = enmPlaybackState;
    }

    public String getEnmPlaybackType() {
        return enmPlaybackType;
    }

    public void setEnmPlaybackType(String enmPlaybackType) {
        this.enmPlaybackType = enmPlaybackType;
    }

    public String getEnmVolume() {
        return enmVolume;
    }

    public void setEnmVolume(String enmVolume) {
        this.enmVolume = enmVolume;
    }

    @Override
    public String toString() {
        return "AdjustmentBean{" +
                "enmMusicSpeed='" + enmMusicSpeed + '\'' +
                ", enmPlaybackState='" + enmPlaybackState + '\'' +
                ", enmPlaybackType='" + enmPlaybackType + '\'' +
                ", enmVolume='" + enmVolume + '\'' +
                '}';
    }
}

package com.player.playlistapplication.controller.adjustments;

import com.player.playlistapplication.helper.EnmMusicSpeed;
import com.player.playlistapplication.helper.EnmPlaybackState;
import com.player.playlistapplication.helper.EnmPlaybackType;
import com.player.playlistapplication.helper.EnmVolume;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.repository.InfMusicRepository;
import com.player.playlistapplication.repository.InfPlaylistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 *     This is one of {@link Player} subclasses to implement Observer Pattern's
 *     settings for {@link Music}s.
 * </p>
 */

public class MusicPlayer extends Player {
    private static final Logger logger = LoggerFactory.getLogger(MusicPlayer.class);
    private Music music;

    public MusicPlayer(Long id,
                       InfPlaylistRepository playlistRepository,
                       InfMusicRepository musicRepository) {
        super(id, playlistRepository, musicRepository);
        this.music = musicRepository.findById(id).get();
    }


    @Override
    public void setMusicSpeed(EnmMusicSpeed enmMusicSpeed) {
        super.enmMusicSpeed = enmMusicSpeed;
    }

    @Override
    public void setPlaybackState(EnmPlaybackState enmPlaybackState) {
        super.enmPlaybackState = enmPlaybackState;
    }

    @Override
    public void setPlaybackType(EnmPlaybackType enmPlaybackType) {
        super.enmPlaybackType = enmPlaybackType;
    }

    @Override
    public void setVolume(EnmVolume enmVolume) {
        super.enmVolume = enmVolume;
    }

    @Override
    public void playVerySlow() {
        logger.info("Music '{}' playing {}.", this.music.getName(), super.enmMusicSpeed);

    }

    @Override
    public void playSlow() {
        logger.info("Music '{}' playing {}.", this.music.getName(), super.enmMusicSpeed);
    }

    @Override
    public void playNormal() {
        logger.info("Music '{}' playing {}.", this.music.getName(), super.enmMusicSpeed);
    }

    @Override
    public void playFast() {
        logger.info("Music '{}' playing {}.", this.music.getName(), super.enmMusicSpeed);
    }

    @Override
    public void playVeryFast() {
        logger.info("Music '{}' playing {}.", this.music.getName(), super.enmMusicSpeed);
    }

    @Override
    public void play() {
        logger.info("Music '{}' {}ed.", this.music.getName(), super.enmPlaybackState);
    }

    @Override
    public void pause() {
        logger.info("Music '{}' {}ed.", this.music.getName(), super.enmPlaybackState);
    }

    @Override
    public void stop() {
        logger.info("Music '{}' {}ed.", this.music.getName(), super.enmPlaybackState);
    }

    @Override
    public void next() {
        logger.info("{} music '{}' playing.", this.music.getName(), super.enmPlaybackState);
    }

    @Override
    public void previous() {
        logger.info("{} music '{}' playing.", this.music.getName(), super.enmPlaybackState);
    }

    @Override
    public void playShuffleType() {
        logger.info("Music '{}' played in {} type.", this.music.getName(), super.enmPlaybackType);
    }

    @Override
    public void repeatOneType() {
        logger.info("Music '{}' played in {} type.", this.music.getName(), super.enmPlaybackType);
    }

    @Override
    public void repeatAllType() {
        logger.info("Music '{}' played in {} type.", this.music.getName(), super.enmPlaybackType);
    }

    @Override
    public void normalType() {
        logger.info("Music '{}' played in {} type.", this.music.getName(), super.enmPlaybackType);
    }

    @Override
    public void increaseVolume() {
        logger.info("Music '{}' volume {}ed.", this.music.getName(), super.enmVolume);
    }

    @Override
    public void decreaseVolume() {
        logger.info("Music '{}' volume {}ed.", this.music.getName(), super.enmVolume);
    }

    @Override
    public void mediumVolume() {
        logger.info("Music '{}' volume set on {}.", this.music.getName(), super.enmVolume);
    }

    @Override
    public String toString() {
        return "MusicPlayer{" +
                "music=" + music.getName() +
                ", enmMusicSpeed=" + enmMusicSpeed +
                ", enmPlaybackState=" + enmPlaybackState +
                ", enmPlaybackType=" + enmPlaybackType +
                ", enmVolume=" + enmVolume +
                '}';
    }
}

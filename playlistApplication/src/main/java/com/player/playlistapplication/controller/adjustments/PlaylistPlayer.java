package com.player.playlistapplication.controller.adjustments;

import com.player.playlistapplication.helper.EnmMusicSpeed;
import com.player.playlistapplication.helper.EnmPlaybackState;
import com.player.playlistapplication.helper.EnmPlaybackType;
import com.player.playlistapplication.helper.EnmVolume;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.model.Playlist;
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
 *     settings for {@link Playlist}s.
 * </p>
 */

public class PlaylistPlayer extends Player {
    private static final Logger logger = LoggerFactory.getLogger(MusicPlayer.class);
    private Playlist playlist;

    public PlaylistPlayer(Long id,
                          InfPlaylistRepository playlistRepository,
                          InfMusicRepository musicRepository) {
        super(id, playlistRepository, musicRepository);
        this.playlist = playlistRepository.findById(id).get();
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
        logger.info("Playlist '{}' playing {}.", playlist.getName(), super.enmMusicSpeed);
    }

    @Override
    public void playSlow() {
        logger.info("Playlist '{}' playing {}.", playlist.getName(), super.enmMusicSpeed);
    }

    @Override
    public void playNormal() {
        logger.info("Playlist '{}' playing {}.", playlist.getName(), super.enmMusicSpeed);
    }

    @Override
    public void playFast() {
        logger.info("Playlist '{}' playing {}.", playlist.getName(), super.enmMusicSpeed);
    }

    @Override
    public void playVeryFast() {
        logger.info("Playlist '{}' playing {}.", playlist.getName(), super.enmMusicSpeed);
    }

    @Override
    public void play() {
        logger.info("Playlist '{}' {}ed.", playlist.getName(), super.enmPlaybackState);
    }

    @Override
    public void pause() {
        logger.info("Playlist '{}' {}ed.", playlist.getName(), super.enmPlaybackState);
    }

    @Override
    public void stop() {
        logger.info("Playlist '{}' {}ed.", playlist.getName(), super.enmPlaybackState);
    }

    @Override
    public void next() {
        logger.info("{} playlist '{}' playing.", playlist.getName(), super.enmPlaybackState);
    }

    @Override
    public void previous() {
        logger.info("{} playlist '{}' playing.", playlist.getName(), super.enmPlaybackState);
    }

    @Override
    public void playShuffleType() {
        logger.info("Playlists '{}' played in {} type.", playlist.getName(), super.enmPlaybackType);
    }

    @Override
    public void repeatOneType() {
        logger.info("Playlists '{}' played in {} type.", playlist.getName(), super.enmPlaybackType);
    }

    @Override
    public void repeatAllType() {
        logger.info("Playlists '{}' played in {} type.", playlist.getName(), super.enmPlaybackType);
    }

    @Override
    public void normalType() {
        logger.info("Playlists '{}' played in {} type.", playlist.getName(), super.enmPlaybackType);
    }

    @Override
    public void increaseVolume() {
        logger.info("Playlist '{}' volume {}ed.", playlist.getName(), super.enmVolume);
    }

    @Override
    public void decreaseVolume() {
        logger.info("Playlist '{}' volume {}ed.", playlist.getName(), super.enmVolume);
    }

    @Override
    public void mediumVolume() {
        logger.info("Playlist '{}' volume set on {}.", playlist.getName(), super.enmVolume);
    }

    @Override
    public String toString() {
        return "PlaylistPlayer{" +
                "playlist=" + playlist.getName() +
                ", enmMusicSpeed=" + enmMusicSpeed +
                ", enmPlaybackState=" + enmPlaybackState +
                ", enmPlaybackType=" + enmPlaybackType +
                ", enmVolume=" + enmVolume +
                '}';
    }
}

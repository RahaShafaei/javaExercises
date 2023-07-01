package com.player.playlistapplication.controller.adjustments;

import com.player.playlistapplication.helper.EnmMusicSpeed;
import com.player.playlistapplication.helper.EnmPlaybackState;
import com.player.playlistapplication.helper.EnmPlaybackType;
import com.player.playlistapplication.helper.EnmVolume;
import com.player.playlistapplication.model.Playlist;
import com.player.playlistapplication.repository.InfMusicRepository;
import com.player.playlistapplication.repository.InfPlaylistRepository;

public class PlaylistPlayer extends Player {
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
        System.out.printf("Playlist '%s' playing %s.%n", playlist.getName(), super.enmMusicSpeed);
    }

    @Override
    public void playSlow() {
        System.out.printf("Playlist '%s' playing %s.%n", playlist.getName(), super.enmMusicSpeed);
    }

    @Override
    public void playNormal() {
        System.out.printf("Playlist '%s' playing %s.%n", playlist.getName(), super.enmMusicSpeed);
    }

    @Override
    public void playFast() {
        System.out.printf("Playlist '%s' playing %s.%n", playlist.getName(), super.enmMusicSpeed);
    }

    @Override
    public void playVeryFast() {
        System.out.printf("Playlist '%s' playing %s.%n", playlist.getName(), super.enmMusicSpeed);
    }

    @Override
    public void play() {
        System.out.printf("Playlist '%s' %sed.%n", playlist.getName(), super.enmPlaybackState);
    }

    @Override
    public void pause() {
        System.out.printf("Playlist '%s' %sed.%n", playlist.getName(), super.enmPlaybackState);
    }

    @Override
    public void stop() {
        System.out.printf("Playlist '%s' %sed.%n", playlist.getName(), super.enmPlaybackState);
    }

    @Override
    public void next() {
        System.out.printf("%s playlist '%s' playing.%n", playlist.getName(), super.enmPlaybackState);
    }

    @Override
    public void previous() {
        System.out.printf("%s playlist '%s' playing.%n", playlist.getName(), super.enmPlaybackState);
    }

    @Override
    public void playShuffleType() {
        System.out.printf("Playlists '%s' played in %s type.%n", playlist.getName(), super.enmPlaybackType);
    }

    @Override
    public void repeatOneType() {
        System.out.printf("Playlists '%s' played in %s type.%n", playlist.getName(), super.enmPlaybackType);
    }

    @Override
    public void repeatAllType() {
        System.out.printf("Playlists '%s' played in %s type.%n", playlist.getName(), super.enmPlaybackType);
    }

    @Override
    public void normalType() {
        System.out.printf("Playlists '%s' played in %s type.%n", playlist.getName(), super.enmPlaybackType);
    }

    @Override
    public void increaseVolume() {
        System.out.printf("Playlist '%s' volume %sed.%n", playlist.getName(), super.enmVolume);
    }

    @Override
    public void decreaseVolume() {
        System.out.printf("Playlist '%s' volume %sed.%n", playlist.getName(), super.enmVolume);
    }

    @Override
    public void mediumVolume() {
        System.out.printf("Playlist '%s' volume set on %s.%n", playlist.getName(), super.enmVolume);
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

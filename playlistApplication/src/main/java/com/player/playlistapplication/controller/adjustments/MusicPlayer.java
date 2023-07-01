package com.player.playlistapplication.controller.adjustments;

import com.player.playlistapplication.helper.EnmMusicSpeed;
import com.player.playlistapplication.helper.EnmPlaybackState;
import com.player.playlistapplication.helper.EnmPlaybackType;
import com.player.playlistapplication.helper.EnmVolume;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.repository.InfMusicRepository;
import com.player.playlistapplication.repository.InfPlaylistRepository;

public class MusicPlayer extends Player {
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
        System.out.printf("Music '%s' playing %s.%n", this.music.getName(), super.enmMusicSpeed);
    }

    @Override
    public void playSlow() {
        System.out.printf("Music '%s' playing %s.%n", this.music.getName(), super.enmMusicSpeed);
    }

    @Override
    public void playNormal() {
        System.out.printf("Music '%s' playing %s.%n", this.music.getName(), super.enmMusicSpeed);
    }

    @Override
    public void playFast() {
        System.out.printf("Music '%s' playing %s.%n", this.music.getName(), super.enmMusicSpeed);
    }

    @Override
    public void playVeryFast() {
        System.out.printf("Music '%s' playing %s.%n", this.music.getName(), super.enmMusicSpeed);
    }

    @Override
    public void play() {
        System.out.printf("Music '%s' %sed.%n", this.music.getName(), super.enmPlaybackState);
    }

    @Override
    public void pause() {
        System.out.printf("Music '%s' %sed.%n", this.music.getName(), super.enmPlaybackState);
    }

    @Override
    public void stop() {
        System.out.printf("Music '%s' %sed.%n", this.music.getName(), super.enmPlaybackState);
    }

    @Override
    public void next() {
        System.out.printf("%s music '%s' playing.%n", this.music.getName(), super.enmPlaybackState);
    }

    @Override
    public void previous() {
        System.out.printf("%s music '%s' playing.%n", this.music.getName(), super.enmPlaybackState);
    }

    @Override
    public void playShuffleType() {
        System.out.printf("Music '%s' played in %s type.%n", this.music.getName(), super.enmPlaybackType);
    }

    @Override
    public void repeatOneType() {
        System.out.printf("Music '%s' played in %s type.%n", this.music.getName(), super.enmPlaybackType);
    }

    @Override
    public void repeatAllType() {
        System.out.printf("Music '%s' played in %s type.%n", this.music.getName(), super.enmPlaybackType);
    }

    @Override
    public void normalType() {
        System.out.printf("Music '%s' played in %s type.%n", this.music.getName(), super.enmPlaybackType);
    }

    @Override
    public void increaseVolume() {
        System.out.printf("Music '%s' volume %sed.%n", this.music.getName(), super.enmVolume);
    }

    @Override
    public void decreaseVolume() {
        System.out.printf("Music '%s' volume %sed.%n", this.music.getName(), super.enmVolume);
    }

    @Override
    public void mediumVolume() {
        System.out.printf("Music '%s' volume set on %s.%n", this.music.getName(), super.enmVolume);
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

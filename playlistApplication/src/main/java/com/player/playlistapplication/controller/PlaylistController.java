package com.player.playlistapplication.controller;

import com.player.playlistapplication.controller.adjustments.Player;
import com.player.playlistapplication.controller.adjustments.Playing;
import com.player.playlistapplication.controller.adjustments.PlayingAdjustments;
import com.player.playlistapplication.controller.smartPlaylist.InfFactoryPlaylistBasedOnSth;
import com.player.playlistapplication.controller.smartPlaylist.PlaylistBasedOnSth;
import com.player.playlistapplication.controller.smartPlaylist.UsePlaylistBasedOnSthInf;
import com.player.playlistapplication.dto.MusicDto;
import com.player.playlistapplication.dto.MusicMapper;
import com.player.playlistapplication.dto.PlaylistDto;
import com.player.playlistapplication.dto.PlaylistMapper;
import com.player.playlistapplication.exception.ItemExistException;
import com.player.playlistapplication.exception.ItemNotFoundException;
import com.player.playlistapplication.helper.*;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.model.Playlist;
import com.player.playlistapplication.repository.InfMusicRepository;
import com.player.playlistapplication.repository.InfPlaylistRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PlaylistController {
    private InfPlaylistRepository playlistRepository;
    private PlaylistMapper playlistMapper;
    private InfMusicRepository musicRepository;
    private MusicMapper musicMapper;
    private UsePlaylistBasedOnSthInf usePlaylistBasedOnSthInf;
    private Playing playing;

    public PlaylistController(InfPlaylistRepository playlistRepository,
                              PlaylistMapper playlistMapper,
                              InfMusicRepository musicRepository,
                              MusicMapper musicMapper,
                              UsePlaylistBasedOnSthInf usePlaylistBasedOnSthInf,
                              Playing playing) {
        this.playlistRepository = playlistRepository;
        this.playlistMapper = playlistMapper;
        this.musicRepository = musicRepository;
        this.musicMapper = musicMapper;
        this.usePlaylistBasedOnSthInf = usePlaylistBasedOnSthInf;
        this.playing = playing;
    }

    @GetMapping("/playlists")
    public List<PlaylistDto> retrieveAllPlaylists() {
        return playlistRepository.findAll()
                .stream()
                .map(playlistMapper::toDto)
                .collect(toList());
    }

    @GetMapping("/playlists/{id}")
    public PlaylistDto retrievePlaylist(@PathVariable Long id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);

        if (playlist.isEmpty())
            throw new ItemNotFoundException("id: " + id);

        return playlistMapper.toDto(playlist.get());
    }

    @GetMapping("/playlists/{id}/musics")
    public List<MusicDto> retrieveMusicsOfPlaylist(@PathVariable Long id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);

        if (playlist.isEmpty())
            throw new ItemNotFoundException("id: " + id);

        return playlist.get()
                .getMusicList()
                .stream()
                .map(musicMapper::toDto)
                .collect(toList());
    }

    @DeleteMapping("/playlists/{id}")
    public void deletePlaylist(@PathVariable Long id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);

        if (playlist.isEmpty())
            throw new ItemNotFoundException("id: " + id);

        playlistRepository.deleteById(id);
    }

    @PostMapping("/playlists")
    public ResponseEntity<Playlist> createPlaylist(@Valid @RequestBody Playlist playlist) {
        Playlist savedPlaylist = playlistRepository.save(playlist);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPlaylist.getPlaylistId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/playlists/{playlistID}/music/{musicId}/add")
    public EntityModel<Playlist> addMusicToPlaylist(@PathVariable Long playlistID,
                                                    @PathVariable Long musicId) {

        Optional<Playlist> playlist = playlistRepository.findById(playlistID);
        Optional<Music> music = musicRepository.findById(musicId);

        if (playlist.get().getMusicList().contains(music.get())) {
            throw new ItemExistException("musicId: " + musicId);
        }

        playlist.get().getMusicList().add(music.get());

        Playlist savedPlaylist = playlistRepository.save(playlist.get());

        EntityModel<Playlist> entityModel = EntityModel.of(savedPlaylist);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrievePlaylist(playlistID));
        entityModel.add(link.withRel("all-playlists"));

        return entityModel;
    }

    @PostMapping("/playlists/{playlistID}/music/{musicId}/remove")
    public EntityModel<Playlist> removeMusicToPlaylist(@PathVariable Long playlistID,
                                                       @PathVariable Long musicId) {

        Optional<Playlist> playlist = playlistRepository.findById(playlistID);
        Optional<Music> music = musicRepository.findById(musicId);

        if (!playlist.get().getMusicList().contains(music.get())) {
            throw new ItemNotFoundException("musicId: " + musicId);
        }

        playlist.get().getMusicList().remove(music.get());

        Playlist savedPlaylist = playlistRepository.save(playlist.get());

        EntityModel<Playlist> entityModel = EntityModel.of(savedPlaylist);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrievePlaylist(playlistID));
        entityModel.add(link.withRel("all-playlists"));

        return entityModel;
    }

    //Smart Playlist  ===================================================

    @GetMapping("/playlists/smart/{smartType}/{name}")
    public List<PlaylistDto> retrieveAllPlaylistOfSmartType(@PathVariable String smartType,
                                                            @PathVariable String name) {

        InfFactoryPlaylistBasedOnSth creation = usePlaylistBasedOnSthInf;
        usePlaylistBasedOnSthInf.setEntryBean(new EntryBean(name));

        List<Playlist> playlists = Stream.of(EnmBasedOnSth.valueOf(smartType.toUpperCase()))
                .map(creation::create)
                .map(PlaylistBasedOnSth::findPlaylistBasedOnSth)
                .flatMap(Collection::stream)
                .toList();

        return playlists
                .stream()
                .map(playlistMapper::toDto)
                .collect(toList());
    }

    @PostMapping("/playlists/smart/{smartType}")
    public EntityModel<Playlist> createSmartPlaylist(@PathVariable String smartType,
                                                     @RequestBody EntryBean entryBean) {
        InfFactoryPlaylistBasedOnSth creation = usePlaylistBasedOnSthInf;
        usePlaylistBasedOnSthInf.setEntryBean(entryBean);

        List<Music> musicList = Stream.of(EnmBasedOnSth.valueOf(smartType.toUpperCase()))
                .map(creation::create)
                .map(PlaylistBasedOnSth::collectingMusic)
                .flatMap(Collection::stream)
                .toList();

        Playlist playlist = new Playlist();
        playlist.setName(entryBean.getPlayListName());
        playlist.setMusicList(musicList);

        Playlist savedPlaylist = playlistRepository.save(playlist);

        EntityModel<Playlist> entityModel = EntityModel.of(savedPlaylist);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrievePlaylist(savedPlaylist.getPlaylistId()));
        entityModel.add(link.withRel("all-playlists"));

        return entityModel;
    }

    // Adjustment test ================================
    @GetMapping("/playlists/adjustment/{adjustmentType}/{id}")
    public void playingByDefaultAdjustment(@PathVariable String adjustmentType,
                                           @PathVariable Long id) {

        playing.setId(id);
        Player player = playing.create(EnmBasedOnSth.valueOf(adjustmentType.toUpperCase()));

        PlayingAdjustments playingAdjustments = new PlayingAdjustments();
        playingAdjustments.addPlayer(player);
        playingAdjustments.playingByDefaultAdjustments();
    }

    @PostMapping("/playlists/adjustment/{adjustmentType}/{id}")
    public void playingByCustomizeAdjustment(@PathVariable String adjustmentType,
                                             @PathVariable Long id,
                                             @RequestBody AdjustmentBean adjustmentBean) {

        playing.setId(id);
        Player player = playing.create(EnmBasedOnSth.valueOf(adjustmentType.toUpperCase()));

        PlayingAdjustments playingAdjustments = new PlayingAdjustments();
        playingAdjustments.addPlayer(player);
        playingAdjustments.changeAdjustmentsAndPlaying(
                EnmMusicSpeed.valueOf(adjustmentBean.getEnmMusicSpeed().toUpperCase()),
                EnmPlaybackState.valueOf(adjustmentBean.getEnmPlaybackState().toUpperCase()),
                EnmPlaybackType.valueOf(adjustmentBean.getEnmPlaybackType().toUpperCase()),
                EnmVolume.valueOf(adjustmentBean.getEnmVolume().toUpperCase())
        );
    }

}

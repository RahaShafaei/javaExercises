package com.player.playlistapplication.controller;

import com.player.playlistapplication.controller.builder.PlaylistBuilder;
import com.player.playlistapplication.controller.smartPlaylist.InfFactoryPlaylistBasedOnSth;
import com.player.playlistapplication.controller.smartPlaylist.PlaylistBasedOnSth;
import com.player.playlistapplication.controller.smartPlaylist.UsePlaylistBasedOnSthInf;
import com.player.playlistapplication.dto.MusicDto;
import com.player.playlistapplication.dto.MusicMapper;
import com.player.playlistapplication.dto.PlaylistDto;
import com.player.playlistapplication.dto.PlaylistMapper;
import com.player.playlistapplication.exception.ItemExistException;
import com.player.playlistapplication.exception.ItemNotFoundException;
import com.player.playlistapplication.helper.EnmBasedOnSth;
import com.player.playlistapplication.helper.EntryBean;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 * Handle all {@link Playlist} interactions.
 * </p>
 */
@RestController
public class PlaylistController {
    private final InfPlaylistRepository playlistRepository;
    private final PlaylistMapper playlistMapper;
    private final InfMusicRepository musicRepository;
    private final MusicMapper musicMapper;
    private final UsePlaylistBasedOnSthInf usePlaylistBasedOnSthInf;

    public PlaylistController(PlaylistBuilder playlistBuilder) {
        this.playlistRepository = playlistBuilder.getPlaylistRepository();
        this.playlistMapper = playlistBuilder.getPlaylistMapper();
        this.musicRepository = playlistBuilder.getMusicRepository();
        this.musicMapper = playlistBuilder.getMusicMapper();
        this.usePlaylistBasedOnSthInf = playlistBuilder.getUsePlaylistBasedOnSthInf();
    }

    /**
     * <p>Find all {@link Playlist}s,convert them to {@link PlaylistDto} and return them.</p>
     *
     * @return list of {@link PlaylistDto}
     */
    @GetMapping("/playlists")
    public List<PlaylistDto> retrieveAllPlaylists() {
        return playlistRepository.findAll()
                .stream()
                .map(playlistMapper::toDto)
                .toList();
    }

    /**
     * <p>Find a specific {@link Playlist} according to taken id,
     * convert it to {@link PlaylistDto} and return it.</p>
     *
     * @return list of {@link PlaylistDto}
     */
    @GetMapping("/playlists/{id}")
    public PlaylistDto retrievePlaylist(@PathVariable Long id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);

        if (playlist.isEmpty())
            throw new ItemNotFoundException("id: " + id);

        return playlistMapper.toDto(playlist.get());
    }

    /**
     * Find a specific {@link Playlist} according to taken id,
     * get all of its {@link Music}, convert them to {@link MusicDto} and return them.
     *
     * @return list of {@link MusicDto}
     */
    @GetMapping("/playlists/{id}/musics")
    public List<MusicDto> retrieveMusicsOfPlaylist(@PathVariable Long id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);

        if (playlist.isEmpty())
            throw new ItemNotFoundException("id: " + id);

        return playlist.get()
                .getMusicList()
                .stream()
                .map(musicMapper::toDto)
                .toList();
    }

    /**
     * Find a specific {@link Playlist} according to taken id and delete it.
     */
    @DeleteMapping("/playlists/{id}")
    public void deletePlaylist(@PathVariable Long id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);

        if (playlist.isEmpty())
            throw new ItemNotFoundException("id: " + id);

        playlistRepository.deleteById(id);
    }

    /**
     * Create a {@link Playlist} by use of taken {@link Playlist} object data.
     *
     * @return {@link ResponseEntity} of {@link Playlist}
     */
    @PostMapping("/playlists")
    public ResponseEntity<PlaylistDto> createPlaylist(@Valid @RequestBody Playlist playlist) {
        Playlist savedPlaylist = playlistRepository.save(playlist);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPlaylist.getPlaylistId())
                .toUri();

        return ResponseEntity.created(location).body(playlistMapper.toDto(savedPlaylist));
    }

    /**
     * Add one of existing {@link Music} to one of existing {@link Playlist}.
     *
     * @return an {@link EntityModel} of {@link Playlist}
     */
    @PostMapping("/playlists/{playlistID}/music/{musicId}/add")
    public ResponseEntity<EntityModel<PlaylistDto>> addMusicToPlaylist(@PathVariable Long playlistID,
                                                                       @PathVariable Long musicId) {

        Optional<Playlist> playlist = playlistRepository.findById(playlistID);
        Optional<Music> music = musicRepository.findById(musicId);

        if (playlist.get().getMusicList().contains(music.get())) {
            throw new ItemExistException("musicId: " + musicId);
        }

        playlist.get().getMusicList().add(music.get());

        Playlist savedPlaylist = playlistRepository.save(playlist.get());

        EntityModel<PlaylistDto> entityModel = EntityModel.of(playlistMapper.toDto(savedPlaylist));

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrievePlaylist(playlistID));
        entityModel.add(link.withRel("playlists"));

        return ResponseEntity.created(link.toUri()).body(entityModel);
    }

    /**
     * Remove one of existing {@link Music} from one of existing {@link Playlist}.
     *
     * @return an {@link EntityModel} of {@link Playlist}
     */
    @PostMapping("/playlists/{playlistID}/music/{musicId}/remove")
    public ResponseEntity<EntityModel<PlaylistDto>> removeMusicToPlaylist(@PathVariable Long playlistID,
                                                                          @PathVariable Long musicId) {

        Optional<Playlist> playlist = playlistRepository.findById(playlistID);
        Optional<Music> music = musicRepository.findById(musicId);

        if (!playlist.get().getMusicList().contains(music.get())) {
            throw new ItemNotFoundException("musicId: " + musicId);
        }

        playlist.get().getMusicList().remove(music.get());

        Playlist savedPlaylist = playlistRepository.save(playlist.get());

        EntityModel<PlaylistDto> entityModel = EntityModel.of(playlistMapper.toDto(savedPlaylist));

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrievePlaylist(playlistID));
        entityModel.add(link.withRel("playlist_link"));

        return ResponseEntity.created(link.toUri()).body(entityModel);
    }

    //Smart Playlist  ===================================================

    /**
     * <p>
     * Retrieve all {@link Playlist}s of specific type({@link EnmBasedOnSth#ARTIST} or
     * {@link EnmBasedOnSth#GENRE}) and specific exist {@link Music}'s ({@link EnmBasedOnSth#ARTIST} or
     * {@link EnmBasedOnSth#GENRE}) name and convert returned {@link Playlist} list to {@link PlaylistDto}.</p>
     *
     * @param smartType a string to specify type of returning {@link Playlist}s.
     *                  should be one of these values {@link EnmBasedOnSth#ARTIST} or
     *                  {@link EnmBasedOnSth#GENRE}.
     * @param name      name of "Artist" or "Genre" that exist in {@link Music} entity.
     * @return an {@link List} of {@link PlaylistDto}
     */
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
                .toList();
    }

    /**
     * <p>
     * Create a {@link Playlist}s of specific type({@link EnmBasedOnSth#ARTIST} or
     * {@link EnmBasedOnSth#GENRE}) and specific exist {@link Music}'s ({@link EnmBasedOnSth#ARTIST} or
     * {@link EnmBasedOnSth#GENRE}) name.</p>
     *
     * @param smartType a string to specify type of returning {@link Playlist}s.
     *                  should be one of these values {@link EnmBasedOnSth#ARTIST} or
     *                  {@link EnmBasedOnSth#GENRE}.
     * @param entryBean a bean of {@link EntryBean} that contains new {@link Playlist} data.
     * @return an {@link EntityModel} of {@link Playlist}
     */
    @PostMapping("/playlists/smart/{smartType}")
    public ResponseEntity<EntityModel<PlaylistDto>> createSmartPlaylist(@PathVariable String smartType,
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

        EntityModel<PlaylistDto> entityModel = EntityModel.of(playlistMapper.toDto(savedPlaylist));

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrievePlaylist(savedPlaylist.getPlaylistId()));
        entityModel.add(link.withRel("playlist_link"));

        return ResponseEntity.created(link.toUri()).body(entityModel);
    }


}

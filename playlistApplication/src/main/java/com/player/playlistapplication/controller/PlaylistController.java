package com.player.playlistapplication.controller;

import com.player.playlistapplication.controller.smartPlaylist.InfFactoryPlaylistBasedOnSth;
import com.player.playlistapplication.controller.smartPlaylist.PlaylistBasedOnSth;
import com.player.playlistapplication.dto.MusicDto;
import com.player.playlistapplication.dto.PlaylistDto;
import com.player.playlistapplication.exception.ItemExistException;
import com.player.playlistapplication.exception.ItemNotFoundException;
import com.player.playlistapplication.helper.EnmBasedOnSth;
import com.player.playlistapplication.helper.EntryBean;
import com.player.playlistapplication.model.Music;
import com.player.playlistapplication.model.Playlist;
import com.player.playlistapplication.service.PlaylistService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RestController
public class PlaylistController {
    PlaylistService playlistService;

    /**
     * <p>Find all {@link Playlist}s,convert them to {@link PlaylistDto} and return them.</p>
     *
     * @return list of {@link PlaylistDto}
     */
    @GetMapping("/playlists")
    public List<PlaylistDto> retrieveAllPlaylists() {
        return playlistService.getPlaylists();
    }

    /**
     * <p>Find a specific {@link Playlist} according to taken id,
     * convert it to {@link PlaylistDto} and return it.</p>
     *
     * @return list of {@link PlaylistDto}
     */
    @GetMapping("/playlists/{id}")
    public PlaylistDto retrievePlaylist(@PathVariable Long id) {
        return playlistService.getPlaylist(id);
    }

    /**
     * Find a specific {@link Playlist} according to taken id,
     * get all of its {@link Music}, convert them to {@link MusicDto} and return them.
     *
     * @return list of {@link MusicDto}
     */
    @GetMapping("/playlists/{id}/musics")
    public List<MusicDto> retrieveMusicsOfPlaylist(@PathVariable Long id) {
        return playlistService.getMusicsOfPlaylist(id);
    }

    /**
     * Find a specific {@link Playlist} according to taken id and delete it.
     */
    @DeleteMapping("/playlists/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id) {
        boolean result = playlistService.deletePlaylist(id);

        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Create a {@link Playlist} by use of taken {@link Playlist} object data.
     *
     * @return {@link ResponseEntity} of {@link Playlist}
     */
    @PostMapping("/playlists")
    public ResponseEntity<PlaylistDto> createPlaylist(@Valid @RequestBody Playlist playlist) {
        PlaylistDto savedPlaylist = playlistService.createPlaylist(playlist);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPlaylist.getPlaylistId())
                .toUri();

        return ResponseEntity.created(location).body(savedPlaylist);
    }

    /**
     * Add one of existing {@link Music} to one of existing {@link Playlist}.
     *
     * @return an {@link EntityModel} of {@link Playlist}
     */
    @PostMapping("/playlists/{playlistID}/music/{musicId}/add")
    public ResponseEntity<EntityModel<PlaylistDto>> addMusicToPlaylist(@PathVariable Long playlistID,
                                                                       @PathVariable Long musicId) {
        EntityModel<PlaylistDto> entityModel = EntityModel.of(
                playlistService.addMusicToPlaylist(playlistID, musicId)
        );

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



        EntityModel<PlaylistDto> entityModel = EntityModel.of(
                playlistService.removeMusicToPlaylist(playlistID, musicId)
        );

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
        return playlistService.getAllPlaylistOfSmartType(smartType, name);
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
        PlaylistDto savedPlaylist = playlistService.createSmartPlaylist(smartType,entryBean);
        EntityModel<PlaylistDto> entityModel = EntityModel.of(savedPlaylist);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrievePlaylist(savedPlaylist.getPlaylistId()));
        entityModel.add(link.withRel("playlist_link"));

        return ResponseEntity.created(link.toUri()).body(entityModel);
    }


}

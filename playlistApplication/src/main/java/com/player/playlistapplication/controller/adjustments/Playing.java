package com.player.playlistapplication.controller.adjustments;

import com.player.playlistapplication.exception.BadParameterException;
import com.player.playlistapplication.helper.EnmBasedOnSth;
import com.player.playlistapplication.repository.InfMusicRepository;
import com.player.playlistapplication.repository.InfPlaylistRepository;
import org.springframework.stereotype.Component;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 *     Specifies which kind of instance of {@link com.player.playlistapplication.controller.adjustments.Player}
 *     should be taken according to taken {@link EnmBasedOnSth}.
 * </p>
 * <p>
 *     This class is another kind of implementing FactoryMethod pattern.
 * </p>
 */
@Component
public class Playing {
    private Long id;
    private final InfPlaylistRepository playlistRepository;
    private final InfMusicRepository musicRepository;

    public Playing(InfPlaylistRepository playlistRepository,
                   InfMusicRepository musicRepository) {
        this.playlistRepository = playlistRepository;
        this.musicRepository = musicRepository;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    /**
     *
     * @param type {@link EnmBasedOnSth}
     * <p>
     *
     * </p>
     * <p>
     *     According to {@link EnmBasedOnSth} value create an instance of {@link Player}'s
     *     subclasses.
     * </p>
     */
    public Player create(EnmBasedOnSth type) {

        return switch (type) {
            case MUSIC -> new MusicPlayer(this.id, this.playlistRepository, this.musicRepository);
            case PLAYLIST -> new PlaylistPlayer(this.id, this.playlistRepository, this.musicRepository);
            default -> throw new BadParameterException("The type is not in defined parameters.");
        };
    }
}

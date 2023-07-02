package com.player.playlistapplication.controller.smartPlaylist;

import com.player.playlistapplication.exception.BadParameterException;
import com.player.playlistapplication.helper.EnmBasedOnSth;
import com.player.playlistapplication.helper.EntryBean;
import com.player.playlistapplication.repository.InfGenreRepository;
import com.player.playlistapplication.repository.InfMusicRepository;
import com.player.playlistapplication.repository.InfPlaylistRepository;
import org.springframework.stereotype.Component;

/**
 * @author Raha
 * @see com.player.playlistapplication.controller.smartPlaylist.InfFactoryPlaylistBasedOnSth
 *
 * <p>
 * The Factory pattern is useful when you need to create objects of different
 * types based on some conditions or criteria. In a playlist application,
 * you might have different types of playlists (e.g., normal playlist,
 * smart playlist based on genre, artist, etc.). The Factory pattern can
 * be used to encapsulate the creation logic and provide a central place
 * to create instances of different playlist types.
 * </p>
 * @since 2023-06-22
 */

@Component
public class UsePlaylistBasedOnSthInf implements InfFactoryPlaylistBasedOnSth {
    private EntryBean entryBean;
    private InfPlaylistRepository playlistRepository;
    private InfMusicRepository musicRepository;
    private InfGenreRepository genreRepository;

    public UsePlaylistBasedOnSthInf(InfPlaylistRepository playlistRepository,
                                    InfMusicRepository musicRepository,
                                    InfGenreRepository genreRepository) {
        this.playlistRepository = playlistRepository;
        this.musicRepository = musicRepository;
        this.genreRepository = genreRepository;
    }

    public EntryBean getEntryBean() {
        return entryBean;
    }

    public void setEntryBean(EntryBean entryBean) {
        this.entryBean = entryBean;
    }

    /**
     * @param type {@link EnmBasedOnSth}
     *             <p>
     *
     *             </p>
     *             <p>
     *             According to {@link EnmBasedOnSth} value create an instance of {@link PlaylistBasedOnSth}'s
     *             subclasses.
     *             </p>
     */
    @Override
    public PlaylistBasedOnSth create(EnmBasedOnSth type) {

        PlaylistBasedOnSth playlistBasedOnSth = switch (type) {
            case ARTIST -> new PlaylistBasedOnArtist(this.entryBean);
            case GENRE -> new PlaylistBasedOnGenre(this.entryBean);
            default -> throw new BadParameterException("The type is not in defined parameters.");
        };

        playlistBasedOnSth.setPlaylistRepository(playlistRepository);
        playlistBasedOnSth.setMusicRepository(musicRepository);
        playlistBasedOnSth.setGenreRepository(genreRepository);

        return playlistBasedOnSth;
    }

}

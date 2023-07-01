package com.player.playlistapplication.controller.smartPlaylist;

import com.player.playlistapplication.dto.PlaylistMapper;
import com.player.playlistapplication.exception.BadParameterException;
import com.player.playlistapplication.helper.EnmPlaylistBasedOnSth;
import com.player.playlistapplication.helper.EntryBean;
import com.player.playlistapplication.repository.InfGenreRepository;
import com.player.playlistapplication.repository.InfMusicRepository;
import com.player.playlistapplication.repository.InfPlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Raha
 * @since 2023-06-22
 * @see com.player.playlistapplication.controller.smartPlaylist.InfFactoryPlaylistBasedOnSth
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

    @Override
    public PlaylistBasedOnSth create(EnmPlaylistBasedOnSth type) {

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

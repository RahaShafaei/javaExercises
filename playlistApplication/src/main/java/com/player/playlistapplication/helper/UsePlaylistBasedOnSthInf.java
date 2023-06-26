package com.player.playlistapplication.helper;

import com.player.playlistapplication.exception.BadParameterException;

public class UsePlaylistBasedOnSthInf implements InfFactoryPlaylistBasedOnSth {
    private Integer fromYear;
    private Integer toYear;
    private String name;

    public UsePlaylistBasedOnSthInf(Integer fromYear, Integer toYear, String name) {
        this.fromYear = fromYear;
        this.toYear = toYear;
        this.name = name;
    }
    @Override
    public PlaylistBasedOnSth create(EnmPlaylistBasedOnSth type) {
        return switch (type) {
            case ARTIST -> new PlaylistBasedOnArtist(this.fromYear, this.toYear, this.name);
            case GENRE -> new PlaylistBasedOnGenre(this.fromYear, this.toYear, this.name);
            default -> throw new BadParameterException("The type is not in defined parameters.");
        };
    }

}

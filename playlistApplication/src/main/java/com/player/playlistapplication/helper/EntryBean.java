package com.player.playlistapplication.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 * To hold music and playlist data for transfer between server and client.
 * </p>
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EntryBean {
    private Integer fromYear;
    private Integer toYear;
    private String name;
    private String playListName;

    public EntryBean(String name) {
        this.name = name;
    }
}

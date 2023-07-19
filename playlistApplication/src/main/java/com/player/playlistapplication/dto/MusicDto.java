package com.player.playlistapplication.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;
/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 *     Because return {@link com.player.playlistapplication.model.Music} as a response object
 *     caused "StackOverflowError", this class created to handle response objects
 *     for {@link com.player.playlistapplication.model.Music}.
 * </p>
 */
@Getter
@Setter
@ToString
public class MusicDto {
    private long musicId;
    private String name;
    private String artist;
    private Integer pubYear;
    private Map<Long, String> playlists;
    private Map<Long, String> genre;
}

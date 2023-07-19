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
 *     Because return {@link com.player.playlistapplication.model.Genre} as a response object
 *     caused "StackOverflowError", this class created to handle response objects
 *     for {@link com.player.playlistapplication.model.Genre}.
 * </p>
 */

@Getter
@Setter
@ToString
public class GenreDto {
    private long genreId;

    private String name;

    private Map<Long, String> musicList;
}

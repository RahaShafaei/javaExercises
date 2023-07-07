package com.player.playlistapplication.controller;

import com.player.playlistapplication.dto.MusicDto;
import com.player.playlistapplication.dto.PlaylistDto;
import com.player.playlistapplication.model.Music;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 * Handle all {@link Music} tests.
 * </p>
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MusicControllerIntegrationTest {
    @Value("${server.port}")
    private int serverPort;

    @LocalServerPort
    private int localServerPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Test retrieved playlists of a music.")
    void testRetrievePlaylistsOfMusic_whenMusicExist_thenReturnStatus() {
        // Arrange Act
        ResponseEntity<List<PlaylistDto>> musicResponse =
                testRestTemplate.exchange(
                        "/musics/1/playlists",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<PlaylistDto>>() {
                        }
                );
        List<PlaylistDto> playlistDtos = musicResponse.getBody();

        // Assert
        Assertions.assertEquals(
                HttpStatus.OK,
                musicResponse.getStatusCode()
        );
        Assertions.assertEquals(
                playlistDtos.size(),
                1,
                "Returned playlist's music numbers seems to be incorrect"
        );
    }

    @Test
    @DisplayName("Can't delete a Music which has Playlist.")
    void testDeleteMusic_whenMusicHasPlaylist_returnsServerError() {
        // Arrange
        ResponseEntity<Void> resp = testRestTemplate.exchange(
                "/musics/1",
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class
        );

        // Assert
        Assertions.assertEquals(
                HttpStatus.INTERNAL_SERVER_ERROR,
                resp.getStatusCode()
        );
    }

    @Test
    @DisplayName("Music can be created")
    void testCreateMusicOfGenre_whenValidDetailsProvided_returnsMusicDetails() throws JSONException {
        // Arrange
        JSONObject musicDetailsRequestJson = new JSONObject();
        musicDetailsRequestJson.put("name", "Test Music");
        musicDetailsRequestJson.put("artist", "Test artist");
        musicDetailsRequestJson.put("pubYear", 2013);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(musicDetailsRequestJson.toString(), headers);

        // Act
        ResponseEntity<MusicDto> createdMusicDetailsEntity = testRestTemplate.postForEntity(
                "/musics/genre/1",
                request,
                MusicDto.class
        );
        MusicDto createdMusicDetails = createdMusicDetailsEntity.getBody();

        // Assert
        Assertions.assertEquals(HttpStatus.CREATED, createdMusicDetailsEntity.getStatusCode());

        assert createdMusicDetails != null;
        Assertions.assertEquals(
                musicDetailsRequestJson.getString("name"),
                createdMusicDetails.getName(),
                "Returned music's name seems to be incorrect"
        );

        Assertions.assertEquals(
                musicDetailsRequestJson.getString("artist"),
                createdMusicDetails.getArtist(),
                "Returned music's artist seems to be incorrect"
        );

        Assertions.assertEquals(
                musicDetailsRequestJson.getInt("pubYear"),
                createdMusicDetails.getPubYear(),
                "Returned music's pubYear seems to be incorrect"
        );
    }
}
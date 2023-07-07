package com.player.playlistapplication.controller;

import com.player.playlistapplication.dto.MusicDto;
import com.player.playlistapplication.dto.PlaylistDto;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlaylistControllerIntegrationTest {
    @Value("${server.port}")
    private int serverPort;

    @LocalServerPort
    private int localServerPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Test retrieved playlists of a music.")
    void testRetrieveMusicsOfPlaylist_whenPlaylistExist_thenReturnStatus() {
        // Arrange Act
        ResponseEntity<List<MusicDto>> musicResponse =
                testRestTemplate.exchange(
                        "/playlists/1/musics",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<MusicDto>>() {
                        }
                );
        List<MusicDto> musicDtos = musicResponse.getBody();

        // Assert
        Assertions.assertEquals(
                HttpStatus.OK,
                musicResponse.getStatusCode()
        );
        Assertions.assertEquals(
                musicDtos.size(),
                3,
                "Returned playlist's music numbers seems to be incorrect"
        );
    }

    @Test
    @DisplayName("Can delete a Playlist.")
    void testDeletePlaylist_whenPlaylistExist_returnsStatus() {
        // Arrange
        ResponseEntity<Void> resp = testRestTemplate.exchange(
                "/playlists/1",
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class
        );

        // Assert
        Assertions.assertEquals(
                HttpStatus.OK,
                resp.getStatusCode()
        );
    }

    @Test
    @DisplayName("Playlist can be created")
    void testCreatePlaylist_whenValidDetailsProvided_returnsPlaylistDetails() throws JSONException {
        // Arrange
        JSONObject playlistDetailsRequestJson = new JSONObject();
        playlistDetailsRequestJson.put("name", "Test Playlist");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(playlistDetailsRequestJson.toString(), headers);

        // Act
        ResponseEntity<PlaylistDto> createdMusicDetailsEntity = testRestTemplate.postForEntity(
                "/playlists",
                request,
                PlaylistDto.class
        );
        PlaylistDto createdPlaylistDetails = createdMusicDetailsEntity.getBody();

        // Assert
        Assertions.assertEquals(HttpStatus.CREATED, createdMusicDetailsEntity.getStatusCode());

        assert createdPlaylistDetails != null;
        Assertions.assertEquals(
                playlistDetailsRequestJson.getString("name"),
                createdPlaylistDetails.getName(),
                "Returned playlist's name seems to be incorrect"
        );
    }

    @Test
    @DisplayName("Add music to playlist")
    void testAddMusicToPlaylist_whenValidMusicIdAndPlaylistIdProvided_returnsPlaylistDetails() {
        ResponseEntity<PlaylistDto> musicResponse =
                testRestTemplate.exchange(
                        "/playlists/1/music/4/add",
                        HttpMethod.POST,
                        null,
                        new ParameterizedTypeReference<PlaylistDto>() {
                        }
                );
        PlaylistDto playlistDto = musicResponse.getBody();

        // Assert
        Assertions.assertEquals(
                HttpStatus.CREATED,
                musicResponse.getStatusCode()
        );
        Assertions.assertEquals(
                playlistDto.getMusicList().size(),
                4,
                "Returned playlist's music numbers seems to be incorrect"
        );
    }

    @Test
    @DisplayName("Remove music to playlist")
    void testRemoveMusicToPlaylist_whenValidMusicIdAndPlaylistIdProvided_returnsPlaylistDetails() {
        ResponseEntity<PlaylistDto> musicResponse =
                testRestTemplate.exchange(
                        "/playlists/1/music/3/remove",
                        HttpMethod.POST,
                        null,
                        new ParameterizedTypeReference<PlaylistDto>() {
                        }
                );
        PlaylistDto playlistDto = musicResponse.getBody();

        // Assert
        Assertions.assertEquals(
                HttpStatus.CREATED,
                musicResponse.getStatusCode()
        );
        Assertions.assertEquals(
                playlistDto.getMusicList().size(),
                2,
                "Returned playlist's music numbers seems to be incorrect"
        );
    }

    @Test
    void retrieveAllPlaylistOfSmartType() {
    }

    @Test
    void createSmartPlaylist() {
    }

    @Test
    void playingByDefaultAdjustment() {
    }

    @Test
    void playingByCustomizeAdjustment() {
    }
}
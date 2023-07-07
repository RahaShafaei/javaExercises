package com.player.playlistapplication.controller;

import com.player.playlistapplication.dto.MusicDto;
import com.player.playlistapplication.model.Genre;
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
 * Handle all {@link Genre} tests.
 * </p>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GenreControllerIntegrationTest {

    @Value("${server.port}")
    private int serverPort;

    @LocalServerPort
    private int localServerPort;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Test retrieved musics of a genre.")
    void testRetrieveMusicsOfGenre_whenGenreExist_thenReturnStatus() {
        // Arrange Act
        ResponseEntity<List<MusicDto>> musicResponse = testRestTemplate.exchange(
                "/genres/1/musics",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MusicDto>>() {
                }
        );
        List<MusicDto> musics = musicResponse.getBody();

        // Assert
        Assertions.assertEquals(
                HttpStatus.OK,
                musicResponse.getStatusCode()
        );
        Assertions.assertEquals(
                musics.size(),
                4,
                "Returned genre's music numbers seems to be incorrect"
        );
    }

    @Test
    @DisplayName("Can't delete a Genre which has music.")
    void testDeleteGenre_whenGenreHasMusic_returnsServerError() {
        // Arrange
        ResponseEntity<Void> resp = testRestTemplate.exchange(
                "/genres/1",
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class);

        // Assert
        Assertions.assertEquals(
                HttpStatus.INTERNAL_SERVER_ERROR,
                resp.getStatusCode()
        );
    }

    @Test
    @DisplayName("Can delete a Genre which doesn't have music.")
    void testDeleteGenre_whenGenreDoesntHaveMusic_returnsOkStatus() {
        // Arrange
        ResponseEntity<Void> resp = testRestTemplate.exchange(
                "/genres/8",
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class);

        // Assert
        Assertions.assertEquals(
                HttpStatus.OK,
                resp.getStatusCode()
        );
    }

    @Test
    @DisplayName("GENRE can be created")
    void testCreateGenre_whenValidDetailsProvided_returnsGenreDetails() throws JSONException {
        // Arrange
        JSONObject genreDetailsRequestJson = new JSONObject();
        genreDetailsRequestJson.put("name", "Test Genre");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(genreDetailsRequestJson.toString(), headers);

        // Act
        ResponseEntity<Genre> createdGenreDetailsEntity = testRestTemplate.postForEntity(
                "/genres",
                request,
                Genre.class
        );
        Genre createdGenreDetails = createdGenreDetailsEntity.getBody();

        // Assert
        Assertions.assertEquals(
                HttpStatus.CREATED,
                createdGenreDetailsEntity.getStatusCode()
        );
        assert createdGenreDetails != null;
        Assertions.assertEquals(
                genreDetailsRequestJson.getString("name"),
                createdGenreDetails.getName(),
                "Returned genre's name seems to be incorrect"
        );
    }
}
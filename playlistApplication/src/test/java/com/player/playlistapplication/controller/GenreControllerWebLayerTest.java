package com.player.playlistapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.player.playlistapplication.dto.GenreMapper;
import com.player.playlistapplication.dto.MusicMapper;
import com.player.playlistapplication.model.Genre;
import com.player.playlistapplication.repository.InfGenreRepository;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GenreController.class)
public class GenreControllerWebLayerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InfGenreRepository genreRepository;

    @MockBean
    private GenreMapper genreMapper;

    @MockBean
    private MusicMapper musicMapper;

    private Genre genre;

    @BeforeEach
    void setup() {
        genre = new Genre();
        genre.setName("Test Genre");
    }

    @Test
    @DisplayName("GENRE can be deleted")
    void testDeleteGenre_whenValidIdProvided_returnsStatus() throws Exception {
        // Arrange
        long genreId = 1L;
        willDoNothing().given(genreRepository).deleteById(genreId);

        // Act
        ResultActions response = mockMvc.perform(delete("/genres/{id}", genreId));

        // Assert
        response.andExpect(status().isOk()).andDo(print());
    }

    @Test
    @DisplayName("GENRE can be created")
    public void testCreateGenre_whenValidUserDetailsProvided_returnsCreatedGenreDetails() throws Exception {
        // Arrange
        when(genreRepository.save(any(Genre.class))).thenReturn(genre);

        // Act
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/genres")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(genre));

        // Assert
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsString = mvcResult.getResponse().getContentAsString();
        Genre createdGenre = new ObjectMapper().readValue(responseBodyAsString, Genre.class);

        Assertions.assertEquals(genre.getName(),
                createdGenre.getName(), "Returned user's name seems to be incorrect");
    }
}

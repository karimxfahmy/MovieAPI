package com.internintelligence.movieapi.controller;

import com.internintelligence.movieapi.model.Movie;
import com.internintelligence.movieapi.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    void getAllMovies_ShouldReturnListOfMovies() throws Exception {
        // Arrange
        Movie movie1 = new Movie(1L, "Inception", "Christopher Nolan", 2010, "Sci-Fi", 8.8);
        Movie movie2 = new Movie(2L, "The Matrix", "Wachowski Sisters", 1999, "Sci-Fi", 8.7);
        when(movieService.getAll()).thenReturn(Arrays.asList(movie1, movie2));

        // Act & Assert
        mockMvc.perform(get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Inception"))
                .andExpect(jsonPath("$[1].title").value("The Matrix"));
    }

    @Test
    void getMovieById_WhenExists_ShouldReturnMovie() throws Exception {
        // Arrange
        Movie movie = new Movie(1L, "Inception", "Christopher Nolan", 2010, "Sci-Fi", 8.8);
        when(movieService.getById(1L)).thenReturn(Optional.of(movie));

        // Act & Assert
        mockMvc.perform(get("/api/movies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Inception"))
                .andExpect(jsonPath("$.director").value("Christopher Nolan"));
    }

    @Test
    void getMovieById_WhenNotExists_ShouldReturn404() throws Exception {
        // Arrange
        when(movieService.getById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/api/movies/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createMovie_ShouldReturnCreatedMovie() throws Exception {
        // Arrange
        Movie movie = new Movie(1L, "Inception", "Christopher Nolan", 2010, "Sci-Fi", 8.8);
        when(movieService.create(any(Movie.class))).thenReturn(movie);

        // Act & Assert
        mockMvc.perform(post("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Inception\",\"director\":\"Christopher Nolan\",\"releaseYear\":2010,\"genre\":\"Sci-Fi\",\"imdbRating\":8.8}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Inception"));
    }

    @Test
    void updateMovie_WhenExists_ShouldReturnUpdatedMovie() throws Exception {
        // Arrange
        Movie updatedMovie = new Movie(1L, "Inception Updated", "Christopher Nolan", 2010, "Sci-Fi", 9.0);
        when(movieService.update(anyLong(), any(Movie.class))).thenReturn(updatedMovie);

        // Act & Assert
        mockMvc.perform(put("/api/movies/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Inception Updated\",\"director\":\"Christopher Nolan\",\"releaseYear\":2010,\"genre\":\"Sci-Fi\",\"imdbRating\":9.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Inception Updated"));
    }

    @Test
    void deleteMovie_ShouldReturnNoContent() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/api/movies/1"))
                .andExpect(status().isNoContent());
    }
}


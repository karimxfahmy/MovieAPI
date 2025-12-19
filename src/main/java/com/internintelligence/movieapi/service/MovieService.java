package com.internintelligence.movieapi.service;

import com.internintelligence.movieapi.model.Movie;
import com.internintelligence.movieapi.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public List<Movie> getAll() {
        return repository.findAll();
    }

    public Optional<Movie> getById(Long id) {
        return repository.findById(id);
    }

    public Movie create(Movie movie) {
        return repository.save(movie);
    }

    public Movie update(Long id, Movie updatedMovie) {
        return repository.findById(id).map(movie -> {
            movie.setTitle(updatedMovie.getTitle());
            movie.setDirector(updatedMovie.getDirector());
            movie.setReleaseYear(updatedMovie.getReleaseYear());
            movie.setGenre(updatedMovie.getGenre());
            movie.setImdbRating(updatedMovie.getImdbRating());
            return repository.save(movie);
        }).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

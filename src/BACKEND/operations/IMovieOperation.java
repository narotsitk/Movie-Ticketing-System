package BACKEND.operations;

import BACKEND.models.Movie;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IMovieOperation{

    public List<Movie> getAllMovies();
    public Movie getById(int id);

}

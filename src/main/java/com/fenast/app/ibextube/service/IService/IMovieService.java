package com.fenast.app.ibextube.service.IService;

import com.fenast.app.ibextube.db.model.Movies;

import java.util.List;

public interface IMovieService {
    List<Movies> getAllMovies();
    Movies findMovieById(int id);
}

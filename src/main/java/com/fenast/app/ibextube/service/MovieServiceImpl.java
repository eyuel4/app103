package com.fenast.app.ibextube.service;

import com.fenast.app.ibextube.db.model.Movies;
import com.fenast.app.ibextube.db.repository.IMoviesRepository;
import com.fenast.app.ibextube.service.IService.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieServiceImpl implements IMovieService {

    @Autowired
    private IMoviesRepository moviesRepository;

    @Override
    public List<Movies> getAllMovies() {
        return moviesRepository.findAll();
    }

    @Override
    public Movies findMovieById(int id) {
       // return moviesRepository.findMoviesById(id);
        return null;
    }
}

package com.fenast.app.ibextube.db.repository;

import com.fenast.app.ibextube.db.model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMoviesRepository extends JpaRepository<Movies, Integer>{
    @Query("SELECT m FROM Movies m JOIN FETCH m.photo p WHERE m.movieId = :movieId ")
    Movies findMoviesById(@Param("movieId") int movieId);
}

package com.fenast.app.ibextube.controller;

import com.fenast.app.ibextube.db.model.Movies;
import com.fenast.app.ibextube.db.model.Photo;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.service.ResponseMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taddesee on 3/22/2018.
 */
@RestController
@RequestMapping("/ibexapi")
public class MovieController {

    @RequestMapping(value = "/movies", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    public List<Movies> getAllMovies() throws Exception
    {
        Movies mv = new Movies();
        mv.setTitle("Black Panther");
        mv.setDescription("best movie");
        mv.setMovieId(100);
        Photo p = new Photo();
        p.setPhotoId(200);
        p.setPhotoName("BlackPanther pic");
        p.setUrl("https://static.boredpanda.com/blog/wp-content/uploads/2016/01/16-year-old-artist-dimitra-milan-1.jpg");
        mv.setPhoto(p);
        List<Movies> moviesList = new ArrayList<>();
        moviesList.add(mv);
        return moviesList;
    }
}

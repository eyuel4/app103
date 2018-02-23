package com.fenast.app.ibextube.db.model.movie;// default package
// Generated Feb 23, 2018 11:30:17 AM by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * MovieGenres generated by hbm2java
 */
@Entity
@Table(name = "movie_genres", catalog = "ibextube")
public class MovieGenres implements java.io.Serializable {

	private int movieGenereId;
	private Genre genre;
	private Movies movies;

	public MovieGenres() {
	}

	public MovieGenres(int movieGenereId, Genre genre, Movies movies) {
		this.movieGenereId = movieGenereId;
		this.genre = genre;
		this.movies = movies;
	}

	@Id

	@Column(name = "movie_genere_id", unique = true, nullable = false)
	public int getMovieGenereId() {
		return this.movieGenereId;
	}

	public void setMovieGenereId(int movieGenereId) {
		this.movieGenereId = movieGenereId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "genre_id", nullable = false)
	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "movie_id", nullable = false)
	public Movies getMovies() {
		return this.movies;
	}

	public void setMovies(Movies movies) {
		this.movies = movies;
	}

}

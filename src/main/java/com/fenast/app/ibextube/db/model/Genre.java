package com.fenast.app.ibextube.db.model;// default package
// Generated Mar 29, 2018 9:00:37 PM by Hibernate Tools 5.2.8.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Genre generated by hbm2java
 */
@Entity
@Table(name = "genre", catalog = "ibextube")
public class Genre implements java.io.Serializable {

	private int genreId;
	private GenreType genreType;
	private String name;
	private String description;
	private Set<MovieGenres> movieGenreses = new HashSet<MovieGenres>(0);

	public Genre() {
	}

	public Genre(int genreId, GenreType genreType) {
		this.genreId = genreId;
		this.genreType = genreType;
	}

	public Genre(int genreId, GenreType genreType, String name, String description, Set<MovieGenres> movieGenreses) {
		this.genreId = genreId;
		this.genreType = genreType;
		this.name = name;
		this.description = description;
		this.movieGenreses = movieGenreses;
	}

	@Id

	@Column(name = "genre_id", unique = true, nullable = false)
	public int getGenreId() {
		return this.genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GENRE_TYPE_id", nullable = false)
	public GenreType getGenreType() {
		return this.genreType;
	}

	public void setGenreType(GenreType genreType) {
		this.genreType = genreType;
	}

	@Column(name = "name", length = 25)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "genre")
	public Set<MovieGenres> getMovieGenreses() {
		return this.movieGenreses;
	}

	public void setMovieGenreses(Set<MovieGenres> movieGenreses) {
		this.movieGenreses = movieGenreses;
	}

}

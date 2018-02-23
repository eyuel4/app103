package com.fenast.app.ibextube.db.model.movie;// default package
// Generated Feb 23, 2018 11:30:17 AM by Hibernate Tools 5.2.3.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * GenreType generated by hbm2java
 */
@Entity
@Table(name = "genre_type", catalog = "ibextube")
public class GenreType implements java.io.Serializable {

	private int id;
	private String name;
	private String description;
	private Set<Genre> genres = new HashSet<Genre>(0);

	public GenreType() {
	}

	public GenreType(int id) {
		this.id = id;
	}

	public GenreType(int id, String name, String description, Set<Genre> genres) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.genres = genres;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", length = 45)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "genreType")
	public Set<Genre> getGenres() {
		return this.genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

}

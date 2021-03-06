package com.fenast.app.ibextube.db.model.resource;// default package
// Generated Mar 30, 2018 10:22:26 PM by Hibernate Tools 5.2.8.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Genre generated by hbm2java
 */
@Entity
@Table(name = "genre", catalog = "ibextube")
public class Genre implements java.io.Serializable {

	private int genreId;
	private String name;
	private String description;
	private Date insDate;
	private Date lastUpdated;

	public Genre() {
	}

	public Genre(int genreId) {
		this.genreId = genreId;
	}

	public Genre(int genreId, String name, String description) {
		this.genreId = genreId;
		this.name = name;
		this.description = description;
	}

	@Id
	@Column(name = "genre_id", unique = true, nullable = false)
	public int getGenreId() {
		return this.genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ins_date", length = 19)
	public Date getInsDate() {
		return this.insDate;
	}

	public void setInsDate(Date insDate) {
		this.insDate = insDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated", length = 19)
	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
}

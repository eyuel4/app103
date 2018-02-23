package com.fenast.app.ibextube.db.model.movie;// default package
// Generated Feb 23, 2018 11:30:17 AM by Hibernate Tools 5.2.3.Final

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Movies generated by hbm2java
 */
@Entity
@Table(name = "movies", catalog = "ibextube")
public class Movies implements java.io.Serializable {

	private int movieId;
	private Photo photo;
	private String title;
	private String length;
	private String description;
	private String releaseDate;
	private String likeVote;
	private String dislikeVote;
	private Double averageRating;
	private Date insDate;
	private Date lastUseDate;
	private Set<MovieGenres> movieGenreses = new HashSet<MovieGenres>(0);
	private Set<MovieRating> movieRatings = new HashSet<MovieRating>(0);
	private Set<MovieArtist> movieArtists = new HashSet<MovieArtist>(0);
	private Set<MovieTags> movieTagses = new HashSet<MovieTags>(0);

	public Movies() {
	}

	public Movies(int movieId, Photo photo) {
		this.movieId = movieId;
		this.photo = photo;
	}

	public Movies(int movieId, Photo photo, String title, String length, String description, String releaseDate,
			String likeVote, String dislikeVote, Double averageRating, Date insDate, Date lastUseDate,
			Set<MovieGenres> movieGenreses, Set<MovieRating> movieRatings, Set<MovieArtist> movieArtists,
			Set<MovieTags> movieTagses) {
		this.movieId = movieId;
		this.photo = photo;
		this.title = title;
		this.length = length;
		this.description = description;
		this.releaseDate = releaseDate;
		this.likeVote = likeVote;
		this.dislikeVote = dislikeVote;
		this.averageRating = averageRating;
		this.insDate = insDate;
		this.lastUseDate = lastUseDate;
		this.movieGenreses = movieGenreses;
		this.movieRatings = movieRatings;
		this.movieArtists = movieArtists;
		this.movieTagses = movieTagses;
	}

	@Id

	@Column(name = "movie_id", unique = true, nullable = false)
	public int getMovieId() {
		return this.movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PHOTO_photo_id", nullable = false)
	public Photo getPhoto() {
		return this.photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	@Column(name = "title", length = 45)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "length", length = 45)
	public String getLength() {
		return this.length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "release_date", length = 45)
	public String getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Column(name = "like_vote", length = 45)
	public String getLikeVote() {
		return this.likeVote;
	}

	public void setLikeVote(String likeVote) {
		this.likeVote = likeVote;
	}

	@Column(name = "dislike_vote", length = 45)
	public String getDislikeVote() {
		return this.dislikeVote;
	}

	public void setDislikeVote(String dislikeVote) {
		this.dislikeVote = dislikeVote;
	}

	@Column(name = "average_rating", precision = 22, scale = 0)
	public Double getAverageRating() {
		return this.averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ins_date", length = 10)
	public Date getInsDate() {
		return this.insDate;
	}

	public void setInsDate(Date insDate) {
		this.insDate = insDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "last_use_date", length = 10)
	public Date getLastUseDate() {
		return this.lastUseDate;
	}

	public void setLastUseDate(Date lastUseDate) {
		this.lastUseDate = lastUseDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "movies")
	public Set<MovieGenres> getMovieGenreses() {
		return this.movieGenreses;
	}

	public void setMovieGenreses(Set<MovieGenres> movieGenreses) {
		this.movieGenreses = movieGenreses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "movies")
	public Set<MovieRating> getMovieRatings() {
		return this.movieRatings;
	}

	public void setMovieRatings(Set<MovieRating> movieRatings) {
		this.movieRatings = movieRatings;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "movies")
	public Set<MovieArtist> getMovieArtists() {
		return this.movieArtists;
	}

	public void setMovieArtists(Set<MovieArtist> movieArtists) {
		this.movieArtists = movieArtists;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "movies")
	public Set<MovieTags> getMovieTagses() {
		return this.movieTagses;
	}

	public void setMovieTagses(Set<MovieTags> movieTagses) {
		this.movieTagses = movieTagses;
	}

}

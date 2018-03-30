package com.fenast.app.ibextube.db.model;// default package
// Generated Mar 29, 2018 9:00:37 PM by Hibernate Tools 5.2.8.Final

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
	private MovieRating movieRating;
	private Photo photo;
	private String title;
	private String length;
	private String description;
	private Date releaseDate;
	private Integer likeVote;
	private Integer dislikeVote;
	private Double averageRating;
	private Date insDate;
	private Date lastUseDate;
	private Set<MovieGenres> movieGenreses = new HashSet<MovieGenres>(0);
	private Set<MovieArtist> movieArtists = new HashSet<MovieArtist>(0);
	private Set<MovieTags> movieTagses = new HashSet<MovieTags>(0);
	private Set<MovieComment> movieComments = new HashSet<MovieComment>(0);

	public Movies() {
	}

	public Movies(int movieId, MovieRating movieRating, Photo photo) {
		this.movieId = movieId;
		this.movieRating = movieRating;
		this.photo = photo;
	}

	public Movies(int movieId, MovieRating movieRating, Photo photo, String title, String length, String description,
			Date releaseDate, Integer likeVote, Integer dislikeVote, Double averageRating, Date insDate,
			Date lastUseDate, Set<MovieGenres> movieGenreses, Set<MovieArtist> movieArtists, Set<MovieTags> movieTagses,
			Set<MovieComment> movieComments) {
		this.movieId = movieId;
		this.movieRating = movieRating;
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
		this.movieArtists = movieArtists;
		this.movieTagses = movieTagses;
		this.movieComments = movieComments;
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
	@JoinColumn(name = "MOVIE_RATING_rating_id", nullable = false)
	public MovieRating getMovieRating() {
		return this.movieRating;
	}

	public void setMovieRating(MovieRating movieRating) {
		this.movieRating = movieRating;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "release_date", length = 10)
	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Column(name = "like_vote")
	public Integer getLikeVote() {
		return this.likeVote;
	}

	public void setLikeVote(Integer likeVote) {
		this.likeVote = likeVote;
	}

	@Column(name = "dislike_vote")
	public Integer getDislikeVote() {
		return this.dislikeVote;
	}

	public void setDislikeVote(Integer dislikeVote) {
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "movies")
	public Set<MovieComment> getMovieComments() {
		return this.movieComments;
	}

	public void setMovieComments(Set<MovieComment> movieComments) {
		this.movieComments = movieComments;
	}

}

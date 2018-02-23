package com.fenast.app.ibextube.db.model.movie;// default package
// Generated Feb 23, 2018 11:30:17 AM by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MovieRatingId generated by hbm2java
 */
@Embeddable
public class MovieRatingId implements java.io.Serializable {

	private int idRating;
	private int movieId;
	private int userIdUser;

	public MovieRatingId() {
	}

	public MovieRatingId(int idRating, int movieId, int userIdUser) {
		this.idRating = idRating;
		this.movieId = movieId;
		this.userIdUser = userIdUser;
	}

	@Column(name = "idRATING", nullable = false)
	public int getIdRating() {
		return this.idRating;
	}

	public void setIdRating(int idRating) {
		this.idRating = idRating;
	}

	@Column(name = "movie_id", nullable = false)
	public int getMovieId() {
		return this.movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	@Column(name = "USER_idUSER", nullable = false)
	public int getUserIdUser() {
		return this.userIdUser;
	}

	public void setUserIdUser(int userIdUser) {
		this.userIdUser = userIdUser;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MovieRatingId))
			return false;
		MovieRatingId castOther = (MovieRatingId) other;

		return (this.getIdRating() == castOther.getIdRating()) && (this.getMovieId() == castOther.getMovieId())
				&& (this.getUserIdUser() == castOther.getUserIdUser());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdRating();
		result = 37 * result + this.getMovieId();
		result = 37 * result + this.getUserIdUser();
		return result;
	}

}

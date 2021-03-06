package com.fenast.app.ibextube.db.model.resource;// default package
// Generated Mar 30, 2018 10:22:26 PM by Hibernate Tools 5.2.8.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * MovieComment generated by hbm2java
 */
@Entity
@Table(name = "movie_comment", catalog = "ibextube")
public class MovieComment implements java.io.Serializable {

	private int commentId;
	private Movies movies;
	private UserDetail userDetail;
	private String comment;
	private Date commentDate;
	private Date insDate;
	private Date lastUpdated;

	public MovieComment() {
	}

	public MovieComment(int commentId, Movies movies, UserDetail userDetail) {
		this.commentId = commentId;
		this.movies = movies;
		this.userDetail = userDetail;
	}

	public MovieComment(int commentId, Movies movies, UserDetail userDetail, String comment, Date commentDate) {
		this.commentId = commentId;
		this.movies = movies;
		this.userDetail = userDetail;
		this.comment = comment;
		this.commentDate = commentDate;
	}

	@Id

	@Column(name = "comment_id", unique = true, nullable = false)
	public int getCommentId() {
		return this.commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MOVIES_movie_id", nullable = false)
	public Movies getMovies() {
		return this.movies;
	}

	public void setMovies(Movies movies) {
		this.movies = movies;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_idUSER", nullable = false)
	public UserDetail getUserDetail() {
		return this.userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	@Column(name = "comment", length = 200)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "comment_date", length = 10)
	public Date getCommentDate() {
		return this.commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
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

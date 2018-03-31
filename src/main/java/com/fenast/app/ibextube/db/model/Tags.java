package com.fenast.app.ibextube.db.model;// default package
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
 * Tags generated by hbm2java
 */
@Entity
@Table(name = "tags", catalog = "ibextube")
public class Tags implements java.io.Serializable {

	private int tagId;
	private Date insDate;
	private Date lastUpdated;
	private String value;


	public Tags() {
	}

	public Tags(int tagId) {
		this.tagId = tagId;
	}

	public Tags(int tagId, Date insDate, Date lastUpdated, String value ) {
		this.tagId = tagId;
		this.insDate = insDate;
		this.lastUpdated = lastUpdated;
		this.value = value;
	}

	@Id

	@Column(name = "tag_id", unique = true, nullable = false)
	public int getTagId() {
		return this.tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
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

	@Column(name = "value", length = 25)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

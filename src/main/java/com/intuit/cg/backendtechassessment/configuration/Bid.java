package com.intuit.cg.backendtechassessment.configuration;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "BID",indexes = { @Index(name="BidIDx", columnList="projectId,amount"),
		@Index(name="BidIDx", columnList="projectId,autoBid")},
uniqueConstraints= {@UniqueConstraint(columnNames={"projectId", "amount"}),
		@UniqueConstraint(columnNames={"projectId", "autoBid"})})
public class Bid {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectId", nullable = false)
    private Project project;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bidderId", nullable = false)
    private Bidder bidder;
	
/*	@Transient @Column(name = "projectId") @NotNull
	private long projectId;
*/	
	@Column(name = "user")
	private String user;

	@Column(name = "amount") @NotNull	
	private Double amount;
	@Column(name = "autoBid")
	private Double autoBid;
	@Temporal(TemporalType.DATE)
	private Date createdDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Bidder getBidder() {
		return bidder;
	}

	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Double getAutoBid() {
		return autoBid;
	}

	public void setAutoBid(Double autoBid) {
		this.autoBid = autoBid;
	}
}

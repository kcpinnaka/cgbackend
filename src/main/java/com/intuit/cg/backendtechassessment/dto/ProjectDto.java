package com.intuit.cg.backendtechassessment.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.intuit.cg.backendtechassessment.configuration.Bidder;
public class ProjectDto implements Serializable {

	private static final long serialVersionUID = -1865039807837957898L;

	private long projectId;
	private long employerid;
	private String name;	
	private Double lowestBid;
	private boolean biddingCompleted;
	private BidderDto bidWinner;
	public long getEmployerId() {
		return employerid;
	}
	public void setEmployerId(long employerid) {
		this.employerid = employerid;
	}
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss Z")	
	private Date endDate;

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getLowestBid() {
		return lowestBid;
	}

	public void setLowestBid(Double lowestBid) {
		this.lowestBid = lowestBid;
	}
	
	public BidderDto getBidWinner() {
		return bidWinner;
	}
	public void setBidWinner(BidderDto bidWinner) {
		this.bidWinner = bidWinner;
	}
	public boolean getBiddingCompleted() {
		return biddingCompleted;
	}

	public void setBiddingCompleted(boolean biddingCompleted) {
		this.biddingCompleted = biddingCompleted;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

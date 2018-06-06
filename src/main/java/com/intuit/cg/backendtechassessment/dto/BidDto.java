package com.intuit.cg.backendtechassessment.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class BidDto implements Serializable {

	private static final long serialVersionUID = -6248811683098109689L;
	private long bidId;
	private long bidderid;
	private String user;
	private long projectId;
	private double amount;
	private Double autoBid;
	public long getBidderId() {
		return bidderid;
	}
	public void setBidderId(long bidderid) {
		this.bidderid = bidderid;
	}
	public long getBidId() {
		return bidId;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public void setBidId(long bidId) {
		this.bidId = bidId;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Double getAutoBid() {
		return autoBid;
	}

	public void setAutoBid(Double autoBid) {
		this.autoBid = autoBid;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

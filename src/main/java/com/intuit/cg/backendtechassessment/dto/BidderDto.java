package com.intuit.cg.backendtechassessment.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class BidderDto implements Serializable {
	private static final long serialVersionUID = 130992148323139954L;
	private long id;
	private String name;
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(long Id) {
		this.id = Id;
	}
}

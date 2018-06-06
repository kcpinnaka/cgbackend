package com.intuit.cg.backendtechassessment.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class EmployerDto implements Serializable {
	private static final long serialVersionUID = 8749972387259888595L;
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

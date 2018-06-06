package com.intuit.cg.backendtechassessment.repository;

import java.util.Date;

import com.intuit.cg.backendtechassessment.dto.ProjectDto;

public interface ProjRepoCustom {	
	void GetMinBid(ProjectDto projectDto,Date endDate);
}

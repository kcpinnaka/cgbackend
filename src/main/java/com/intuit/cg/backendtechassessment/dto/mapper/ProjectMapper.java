package com.intuit.cg.backendtechassessment.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intuit.cg.backendtechassessment.dto.ProjectDto;
import com.intuit.cg.backendtechassessment.configuration.Project;

@Mapper(componentModel="spring")
public interface ProjectMapper {
	
	ProjectMapper INSTANCE = Mappers.getMapper( ProjectMapper.class );
	
	Project projectDtoToProject(ProjectDto projectDto);
	
	@Mapping(source = "id", target = "projectId")
    ProjectDto projectToProjectDto(Project project);
    
}

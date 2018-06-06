package com.intuit.cg.backendtechassessment.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intuit.cg.backendtechassessment.dto.EmployerDto;
import com.intuit.cg.backendtechassessment.configuration.Employer;

@Mapper
public interface EmployerMapper {
	
	EmployerMapper INSTANCE = Mappers.getMapper( EmployerMapper.class );
	
	Employer employerDtoToEmployer(EmployerDto bidDto);
	
	EmployerDto employerToEmployerDto(Employer bid);    
}

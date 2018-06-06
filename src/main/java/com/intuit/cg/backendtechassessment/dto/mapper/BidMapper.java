package com.intuit.cg.backendtechassessment.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intuit.cg.backendtechassessment.dto.BidDto;
import com.intuit.cg.backendtechassessment.dto.ProjectDto;
import com.intuit.cg.backendtechassessment.configuration.Bid;
import com.intuit.cg.backendtechassessment.configuration.Project;

@Mapper
public interface BidMapper {
	
	BidMapper INSTANCE = Mappers.getMapper( BidMapper.class );
	
	Bid bidDtoToBid(BidDto bidDto);
	
	@Mapping(source = "id", target = "bidId")
	BidDto bidToBidDto(Bid bid);    
}

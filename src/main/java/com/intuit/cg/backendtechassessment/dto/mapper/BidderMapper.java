package com.intuit.cg.backendtechassessment.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intuit.cg.backendtechassessment.dto.BidDto;
import com.intuit.cg.backendtechassessment.dto.BidderDto;
import com.intuit.cg.backendtechassessment.dto.ProjectDto;
import com.intuit.cg.backendtechassessment.configuration.Bid;
import com.intuit.cg.backendtechassessment.configuration.Bidder;
import com.intuit.cg.backendtechassessment.configuration.Project;

@Mapper
public interface BidderMapper {
	
	BidderMapper INSTANCE = Mappers.getMapper( BidderMapper.class );
	
	Bidder bidderDtoToBidder(BidderDto bidDto);
	
	BidderDto bidderToBidderDto(Bidder bid);

}

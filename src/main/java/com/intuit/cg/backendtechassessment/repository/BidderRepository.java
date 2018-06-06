package com.intuit.cg.backendtechassessment.repository;

import org.springframework.data.repository.CrudRepository;

import com.intuit.cg.backendtechassessment.configuration.Bid;
import com.intuit.cg.backendtechassessment.configuration.Bidder;

public interface BidderRepository extends CrudRepository<Bidder, Long>{

}
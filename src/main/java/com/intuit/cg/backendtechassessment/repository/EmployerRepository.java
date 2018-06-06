package com.intuit.cg.backendtechassessment.repository;

import org.springframework.data.repository.CrudRepository;

import com.intuit.cg.backendtechassessment.configuration.Bid;
import com.intuit.cg.backendtechassessment.configuration.Employer;

public interface EmployerRepository extends CrudRepository<Employer, Long>{

}
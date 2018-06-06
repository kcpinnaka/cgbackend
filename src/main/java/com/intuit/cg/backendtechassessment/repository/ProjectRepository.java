package com.intuit.cg.backendtechassessment.repository;

import org.springframework.data.repository.CrudRepository;
import com.intuit.cg.backendtechassessment.configuration.Project;

public interface ProjectRepository extends CrudRepository<Project, Long>{
}
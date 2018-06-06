package com.intuit.cg.backendtechassessment.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.cg.backendtechassessment.dto.BidDto;
import com.intuit.cg.backendtechassessment.dto.BidderDto;
import com.intuit.cg.backendtechassessment.dto.EmployerDto;
import com.intuit.cg.backendtechassessment.dto.ProjectDto;
import com.intuit.cg.backendtechassessment.dto.mapper.BidMapper;
import com.intuit.cg.backendtechassessment.dto.mapper.BidderMapper;
import com.intuit.cg.backendtechassessment.dto.mapper.EmployerMapper;
import com.intuit.cg.backendtechassessment.dto.mapper.ProjectMapper;
import com.intuit.cg.backendtechassessment.configuration.Bid;
import com.intuit.cg.backendtechassessment.configuration.Bidder;
import com.intuit.cg.backendtechassessment.configuration.Employer;
import com.intuit.cg.backendtechassessment.configuration.Project;
import com.intuit.cg.backendtechassessment.controller.requestmappings.RequestMappings;
import com.intuit.cg.backendtechassessment.repository.BidRepository;
import com.intuit.cg.backendtechassessment.repository.BidderRepository;
import com.intuit.cg.backendtechassessment.repository.EmployerRepository;
import com.intuit.cg.backendtechassessment.repository.ProjRepoCustomImpl;
import com.intuit.cg.backendtechassessment.repository.ProjectRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
@RestController
public class AuctionController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuctionController.class);
	
	@Autowired
	private ProjectRepository projectRepository;	
	@Autowired
	private ProjRepoCustomImpl projRepoCustom;	
	@Autowired
	private BidRepository bidRepository;
	@Autowired
	private BidderRepository bidderRepository;
	@Autowired
	private EmployerRepository employerRepository;
	@RequestMapping(value = RequestMappings.CREATE_EMPLOYER, method = RequestMethod.POST)
	public long createEmployer(@RequestBody EmployerDto EmployerDto) {
		logger.info("createEmployer===>"+EmployerDto);		
		Employer Employer = EmployerMapper.INSTANCE.employerDtoToEmployer(EmployerDto);
		employerRepository.save(Employer);
		return Employer.getId();
	}
	@RequestMapping(value = RequestMappings.CREATE_BIDDER, method = RequestMethod.POST)
	public long createBidder(@RequestBody BidderDto BidderDto) {
		logger.info("createBidder===>"+BidderDto);		
		Bidder Bidder = BidderMapper.INSTANCE.bidderDtoToBidder(BidderDto);
		bidderRepository.save(Bidder);
		return Bidder.getId();
	}
		
	@RequestMapping(value = RequestMappings.CREATE_PROJECT, method = RequestMethod.POST)
	public long createProject(@RequestBody ProjectDto projectDto) {
		logger.info("createProject===>"+projectDto);		
		Project project = ProjectMapper.INSTANCE.projectDtoToProject(projectDto);
		project.setEmployer(employerRepository.findById(projectDto.getEmployerId()).get());
		logger.info("projectDtoToProject===>"+project);		
		projectRepository.save(project);
		return project.getId();
	}
	@RequestMapping(value = RequestMappings.PROJECTS, method = RequestMethod.GET
			, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public  ResponseEntity<List<ProjectDto>> getProjects() {
		List<ProjectDto> projects = new ArrayList<ProjectDto>(); 
		logger.info("getProjects===>");
		projectRepository.findAll().forEach(p -> projects.add(getProject(p.getId())));
		return new ResponseEntity<List<ProjectDto>>(projects, HttpStatus.OK);
	}
	@RequestMapping(value = RequestMappings.BIDS, method = RequestMethod.GET
			, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public  ResponseEntity<List<BidDto>> getBids() {
		List<BidDto> bidsDto = new ArrayList<BidDto>(); 
		logger.info("getProjects===>");		
		bidRepository.findAll().forEach(b -> bidsDto.add(BidMapper.INSTANCE.bidToBidDto(b)));		
		return new ResponseEntity<List<BidDto>>(bidsDto, HttpStatus.OK);
	}
	@RequestMapping(value = RequestMappings.BIDDERS,method = RequestMethod.GET
			, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public  ResponseEntity<List<BidderDto>> getBidders() {
		List<BidderDto> biddersDto = new ArrayList<BidderDto>(); 
		logger.info("getProjects===>");		
		bidderRepository.findAll().forEach(b -> biddersDto.add(BidderMapper.INSTANCE.bidderToBidderDto(b)));		
		return new ResponseEntity<List<BidderDto>>(biddersDto, HttpStatus.OK);
	}
	@RequestMapping(value = RequestMappings.EMPLOYERS,method = RequestMethod.GET
			, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public  ResponseEntity<List<EmployerDto>> getEmployers() {
		List<EmployerDto> employersDto = new ArrayList<EmployerDto>(); 
		logger.info("getProjects===>");		
		employerRepository.findAll().forEach(b -> employersDto.
				add(EmployerMapper.INSTANCE.employerToEmployerDto(b)));		
		return new ResponseEntity<List<EmployerDto>>(employersDto, HttpStatus.OK);
	}
	public  ProjectDto getProject(long projectId) {
		logger.info("getProject===>"+projectId);
		Project project = projectRepository.findById(projectId).get();
		ProjectDto projectDto = ProjectMapper.INSTANCE.projectToProjectDto(project);
		projRepoCustom.GetMinBid(projectDto, project.getEndDate());
		return projectDto;
	}
	
	@RequestMapping(value = RequestMappings.GET_PROJECT, method = RequestMethod.GET
			, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public  ResponseEntity<ProjectDto> getProjectApi(@PathVariable("projectId") long projectId) {
		logger.info("getProject===>"+projectId);
		return new ResponseEntity<ProjectDto>(getProject(projectId), HttpStatus.OK);
	}
	
	@RequestMapping(value = RequestMappings.CREATE_BID, method = RequestMethod.POST)
	public long createBid(@RequestBody BidDto bidDto) throws IOException {
		logger.info("createBid===>"+bidDto);
		Project project = projectRepository.findById(bidDto.getProjectId()).get();
		if((new Date()).after(project.getEndDate())){
			throw new IOException("Bidding is ended for this project");
		}
		Bid bid = BidMapper.INSTANCE.bidDtoToBid(bidDto);
		//bid.setUser(getCurrentUser());
		bid.setBidder(bidderRepository.findById(bidDto.getBidderId()).get());
		bid.setProject(projectRepository.findById(bidDto.getProjectId()).get());
		logger.info("createBid Bid obj ===>"+bid);
		bidRepository.save(bid);
		return bid.getId();
	}	
/*	private String getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
*/}

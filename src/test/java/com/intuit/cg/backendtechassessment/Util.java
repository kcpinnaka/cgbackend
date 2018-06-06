package com.intuit.cg.backendtechassessment;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.cg.backendtechassessment.controller.requestmappings.RequestMappings;
import com.intuit.cg.backendtechassessment.dto.BidDto;
import com.intuit.cg.backendtechassessment.dto.BidderDto;
import com.intuit.cg.backendtechassessment.dto.EmployerDto;
import com.intuit.cg.backendtechassessment.dto.ProjectDto;
import org.junit.Before;
//@WebAppConfiguration
@Component
//@ComponentScan({ "com.intuit.cg.backendtechassessment" })
public class Util {
/*    @Autowired
    private AuctionController auctionController;
*/    @Autowired
    private MockMvc mockMvc;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private long testbidderid;
	private long testemployerid;
    @Before
    public void setUp() throws Exception {
		BidderDto bidderDto = new BidderDto();
		bidderDto.setName("bidder1");
		MvcResult  result = UtilPost(bidderDto,RequestMappings.CREATE_BIDDER);
		testbidderid = Long.parseLong(result.getResponse().getContentAsString());
		EmployerDto EmployerDto = new EmployerDto();
		EmployerDto.setName("Employer1");
		result = UtilPost(EmployerDto,RequestMappings.CREATE_EMPLOYER);    	
		testemployerid = Long.parseLong(result.getResponse().getContentAsString());
    }
	public ResultActions UtilGet(String uri) throws Exception {
		ResultActions req = this.mockMvc.perform(get(uri)
    		.accept(MediaType.APPLICATION_JSON_VALUE)
    		.contentType(MediaType.APPLICATION_JSON_VALUE));
    	MvcResult result = req.andReturn();
    	logger.info("get uri {} response:{}",uri,result.getResponse().getContentAsString());
    	return req;
	}
    
	public <T> MvcResult UtilPost(T obj,String uri) throws Exception {
    	ObjectMapper mapper = new ObjectMapper();
    	String js = mapper.writeValueAsString(obj);
    	MvcResult result = this.mockMvc.perform(post(uri)
        		.content(js).accept(MediaType.APPLICATION_JSON_VALUE)
        		.contentType(MediaType.APPLICATION_JSON_VALUE))
        		.andReturn();
    	logger.info("post uri {} response:{}",uri,result.getResponse().getContentAsString());
    	return result;
	}
    
	public long UtilCreateProject(Date endDate) throws Exception {
		ProjectDto projectDto = new ProjectDto();
		projectDto.setEmployerId(testemployerid);
		projectDto.setEndDate(endDate);
    	ObjectMapper mapper = new ObjectMapper();
    	String js = mapper.writeValueAsString(projectDto);
    	logger.info("create project req: {}",js);
    	ResultActions req =  
    	this.mockMvc.perform(post(RequestMappings.CREATE_PROJECT)
        		.content(js).accept(MediaType.APPLICATION_JSON_VALUE)
        		.contentType(MediaType.APPLICATION_JSON_VALUE));
    	MvcResult result = req.andReturn();
    	logger.info("create project response:{}",result.getResponse().getContentAsString());
    	req.andExpect(status().isOk());
		long id = Long.parseLong(result.getResponse().getContentAsString());
    	logger.info("create proj id:{}",id);
    	return id;
	}
	//returns the future date after seconds secs parameter 
    Date UtilGetDate(int secs) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.SECOND, secs);
        return cal.getTime();    
     }

	public void UtilCreateBid(long projectId,double bid,Double autoBid,ResultMatcher res) throws Exception {
		BidDto bidDto = new BidDto();
		bidDto.setBidderId(testbidderid);
		bidDto.setProjectId(projectId);
		bidDto.setAmount(bid);
		bidDto.setAutoBid(autoBid);
    	ObjectMapper mapper = new ObjectMapper();
    	String js = mapper.writeValueAsString(bidDto);
    	ResultActions req = this.mockMvc.perform(post(RequestMappings.CREATE_BID)
    		.content(js).accept(MediaType.APPLICATION_JSON_VALUE)
    		.contentType(MediaType.APPLICATION_JSON_VALUE));
    	MvcResult result = req.andReturn();
    	logger.info("create bid response:{}",result.getResponse().getContentAsString());
    	req.andExpect(res);
	}

	public void UtilGetProjectById(long projectId,Double lowestBid) throws Exception {
		ResultActions req = 
		this.mockMvc.perform(get("/get-project/"+projectId)
    		.accept(MediaType.APPLICATION_JSON_VALUE)
    		.contentType(MediaType.APPLICATION_JSON_VALUE));
    	MvcResult result = req.andReturn();
    	logger.info("get project response:{}",result.getResponse().getContentAsString());
    	ResultActions  res = req.andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.projectId").value(projectId));
    	if(lowestBid!=null)
    		res.andExpect(jsonPath("$.lowestBid").value(lowestBid));
    	logger.info("get project passed");
	}
}
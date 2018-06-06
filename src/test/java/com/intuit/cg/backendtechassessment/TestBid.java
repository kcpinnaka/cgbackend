package com.intuit.cg.backendtechassessment;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.intuit.cg.backendtechassessment.controller.requestmappings.RequestMappings;
import org.junit.Before;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=ApplicationTestConfig.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class TestBid {
    @Autowired
    private MockMvc mockMvc;
	@Autowired
	private Util util;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Before
	public void setUp() throws Exception {
		util.setUp();
	}
	@Test
	public void testBidForEndedProject() throws Exception {
		Date endDate = util.UtilGetDate(1);
		long projectId = util.UtilCreateProject(endDate);
		while((new Date()).before(endDate));
		util.UtilCreateBid(projectId,(double)100,70.0,status().isBadRequest());	
	}
	@Test
	public void testDuplicateAutoBid() throws Exception {
		long projectId = util.UtilCreateProject(util.UtilGetDate(60 * 60));
		util.UtilCreateBid(projectId,(double)90,70.0,status().isOk());
		util.UtilCreateBid(projectId,(double)100,70.0,status().isBadRequest());	
	}
	@Test
	public void testDuplicateBid() throws Exception {
		long projectId = util.UtilCreateProject(util.UtilGetDate(60 * 60));
		util.UtilCreateBid(projectId,(double)90,null,status().isOk());
		util.UtilCreateBid(projectId,(double)90,(double)20,status().isBadRequest());		
	}
	public void testCreateBid() throws Exception {
		long projectId = util.UtilCreateProject(util.UtilGetDate(60 * 60));
		util.UtilCreateBid(projectId,(double)70,(double)60,status().isOk());
		util.UtilCreateBid(projectId,(double)90,null,status().isOk());
		util.UtilGetProjectById(projectId,(double)70);
		util.UtilCreateBid(projectId,(double)60,null,status().isOk());
		util.UtilCreateBid(projectId,(double)50,(double)40,status().isOk());
		util.UtilGetProjectById(projectId,(double)50);
		//duplicate bid,should fail
		util.UtilCreateBid(projectId,(double)60,(double)20,status().isBadRequest());
		//duplicate auto bid,should fail
		util.UtilCreateBid(projectId,(double)40,(double)40,status().isBadRequest());
		//biiding didnt end, so we dont consider autobid value yet
		util.UtilGetProjectById(projectId,(double)50);
		util.UtilGet(RequestMappings.PROJECTS);
		util.UtilGet(RequestMappings.BIDS);
	}
}
package com.intuit.cg.backendtechassessment;
import com.intuit.cg.backendtechassessment.Util;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.junit.Before;
import org.junit.Test;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=ApplicationTestConfig.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class TestAutoBid {
	@Autowired
    private Util util;
	private final Logger logger = LoggerFactory.getLogger(getClass());
    @Before
    public void setUp() throws Exception {
    	util.setUp();
    }
	@Test
	public void testAutoBid() throws Exception {
		Date endDate = util.UtilGetDate(30);
		long projectId = util.UtilCreateProject(endDate);
		util.UtilCreateBid(projectId,(double)170,null,status().isOk());
		//multiple null's allowed for autobid
		util.UtilCreateBid(projectId,(double)160,null,status().isOk());
		util.UtilCreateBid(projectId,(double)180,(double)140,status().isOk());
		//wait till we past the enddate 
		while((new Date()).before(endDate));
		util.UtilGetProjectById(projectId,(double)140);
	}
}
/*package com.intuit.cg.backendtechassessment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.junit.Before;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=ApplicationTestConfig.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class TestCreateProjNoBids {
	@Autowired
	private Util util;
	@Before
	public void setUp() throws Exception {
		util.setUp();
	}
	@Test
	@WithMockUser(username = "admin", authorities = { "ADMIN", "USER" })
	@WithUserDetails("admin")
	public void testCreateProject() throws Exception {
		long projectId = util.UtilCreateProject(util.UtilGetDate(10));
		util.UtilGetProjectById(projectId,null);
	}
}*/
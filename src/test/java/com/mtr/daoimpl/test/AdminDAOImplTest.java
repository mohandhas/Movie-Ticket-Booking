package com.mtr.daoimpl.test;

import static org.junit.Assert.assertNotNull;

import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.mtr.daoimpl.AdminDAOImpl;
import com.mtr.pojo.Admin;

public class AdminDAOImplTest {
	
	static AdminDAOImpl adminDAOImpl;
	static Logger logger;
	static DataSource dataSource;
	static ApplicationContext context;
	
	@Before
	public void setUp() throws Exception 
	{	
		context = new FileSystemXmlApplicationContext("/Users/Sathya/Desktop/MovieTicketBooking/src/main/webapp/WEB-INF/DispatcherServlet-servlet.xml");
		dataSource = (DataSource) context.getBean("dataSource");
		adminDAOImpl = new AdminDAOImpl();
		logger= Logger.getLogger("AdminDAOImpl.class");
		BasicConfigurator.configure();
	}
	
	@Test
	public void findAdmin() {
		Admin result = adminDAOImpl.findAdmin("admin");
		assertNotNull(result);
		logger.info("Admin Id: "+ result.getId());
	}
}

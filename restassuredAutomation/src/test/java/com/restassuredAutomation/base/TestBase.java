package com.restassuredAutomation.base;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.*;
import org.testng.annotations.BeforeClass;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID="1";// hard coded input 
	
	public  static Logger log;  
	
	@BeforeClass
	public void setUp()
	{
//		log=LogManager.getLogger();
//		PropertyConfigurator.configure("Log4j.properties");
//		log.setLevel(Level.DEBUG);
	}
}

package com.restassuredAutomation.testcases2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.restassuredAutomation.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Get_Single_Employee_Record extends TestBase{
    
	RequestSpecification httpRequest;
	Response response;
    Logger log1=LogManager.getLogger(TC002_Get_Single_Employee_Record.class);
	@BeforeClass
	void getAllEmployee() throws Exception
	{
		log1.info("---------TC002_Get_Single_Employee_Record--------");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		//POST request
	    response=httpRequest.request(Method.GET,"/employee/"+empID);
	    Thread.sleep(5000);
	}
	@Test
	void check_responseBody()
	{
		
		String respBody=response.getBody().asString();
		Assert.assertTrue(respBody.contains(empID));
	}
	@Test
	void checkStatusCode()
	{
		
		int statusCode=response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime()
	{
		
		long responsetime=response.getTime();
		Assert.assertTrue(responsetime<2000);
	}
	@Test
	void checkContentType()
	{
		
		String contentType=response.getContentType();
		Assert.assertEquals(contentType,"application/json");
	}
	@Test
	void checkServerType()
	{
		
		String serverType=response.header("Server");
		Assert.assertEquals(serverType,"nginx/1.21.6");
	}
	@Test
	void checkContentLength()
	{
		
		String contentLength=response.header("Content-Length");
		Assert.assertTrue(Integer.parseInt(contentLength)>100);
	}
	
	@AfterClass
	void tearDown()
	{
		log1.info("---------Finished TC002_Get_Single_Employee_Record--------");
	}
}

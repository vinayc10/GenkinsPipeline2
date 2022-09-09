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


public class TC001_Get_All_Employees extends TestBase{
     Logger log=LogManager.getLogger(TC001_Get_All_Employees.class);
	
	@BeforeClass
	void getAllEmployee() throws Exception
	{
		log.info("---------Started TC001_Get_All_Employees--------");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		//POST request
	    response=httpRequest.request(Method.GET,"/employees");
	    Thread.sleep(5000);
	}
	
	@Test
	void check_responseBody()
	{
		log.info("---------Checking Response Body--------");
		String respBody=response.getBody().asString();
		log.info("Response Body==>"+respBody);
		Assert.assertTrue(respBody!=null);
	}
	
	@Test
	void checkStatusCode()
	{
		log.info("---------Checking Status code--------");
		int statusCode=response.getStatusCode();
		log.info("Status Code==>"+statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime()
	{
		log.info("---------Checking Response Time--------");
		long responsetime=response.getTime();
		log.info("Response time==>"+responsetime);
		if(responsetime>2000)
		{
			log.warn("Respnse Time is Greater than 2000");
			
		}
		Assert.assertTrue(responsetime<10000);
	}
	@Test
	void checkStatusLine()
	{
		log.info("---------Checking Status Line--------");
		String statusLine=response.getStatusLine();
		log.info("Status Line==>"+statusLine);
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
	}
	@Test
	void checkContentType()
	{
		log.info("---------Checking Content Type--------");
		String contentType=response.getContentType();
		log.info("Content type==>"+contentType);
		Assert.assertEquals(contentType,"application/json");
	}
	@Test
	void checkServerType()
	{
		log.info("---------Checking Server Type--------");
		String serverType=response.header("Server");
		log.info("Server type==>"+serverType);
		Assert.assertEquals(serverType,"nginx");
	}
	@Test
	void checkContentEncoding()
	{
		log.info("---------Checking Content Encoding--------");
		String contentEncoding=response.header("content-Encoding");
		log.info("Content Encoding==>"+contentEncoding);
		Assert.assertEquals(contentEncoding,"gzip");
	}
	@Test
	void checkContentLength()
	{
		log.info("---------Checking Content Length--------");
		String contentLength=response.header("Content-Length");
		log.info("Content Length==>"+contentLength);
		if(Integer.parseInt(contentLength)<100)
		{
			log.info("Content Length is Less than 100");
		}
		Assert.assertTrue(Integer.parseInt(contentLength)>100);
	}
	@Test
	void checkCookies()
	{
		log.info("---------Checking Cookies--------");
		String cookie=response.getCookie("PHPSESSID"); 
		
	}
	@AfterClass
	void tearDown()
	{
		log.info("---------Finished TC001_Get_All_Employees--------");
	}
}

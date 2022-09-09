package com.restassuredAutomation.testcases2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.restassuredAutomation.base.TestBase;
import com.restassuredAutomation.utitlities.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_PUT_Employee_Record extends TestBase{
    
	
	    RequestSpecification httpRequest;
	    Response response;
		String empName=RestUtils.empName();
		String empSal=RestUtils.empSal();
		String empAge=RestUtils.empAge();
		  Logger log3=LogManager.getLogger(TC004_PUT_Employee_Record.class);
		@BeforeClass
		void updateEmployee() throws Exception {
			log3.info("---------Started TC004_PUT_Employee_Record--------");
			// Specify baseURI
			RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";

			// Create Request Object
			httpRequest = RestAssured.given();

			// Request payload Sending along with POST request
			JSONObject requestParams = new JSONObject();
			requestParams.put("Name", empName);
			requestParams.put("Salary", empSal);
			requestParams.put("Age", empAge);
		

			httpRequest.header("Content-Type", "application/json");
			//Add Json to the body
			httpRequest.body(requestParams.toJSONString());

			// Response Object
			response = httpRequest.request(Method.PUT, "/update/"+empID);
			Thread.sleep(5000); // Waiting for response
		}
		
		@Test
		void check_responseBody()
		{
			
			String respBody=response.getBody().asString();
			Assert.assertTrue(respBody.contains(empName));
			Assert.assertTrue(respBody.contains(empSal));
			Assert.assertTrue(respBody.contains(empAge));
		}
		@Test
		void checkStatusCode()
		{
			
			int statusCode=response.getStatusCode();
			Assert.assertEquals(statusCode, 200);
		}
		@Test
		void checkStatusLine()
		{
			
			String statusLine=response.getStatusLine();
			Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
		}
		@Test
		void checkResponseTime()
		{
			
			long responsetime=response.getTime();
			Assert.assertTrue(responsetime<10000);
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
			Assert.assertEquals(serverType,"nginx");
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
			log3.info("---------Finished TC004_PUT_Employee_Record--------");
		}
}

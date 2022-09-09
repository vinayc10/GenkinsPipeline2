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
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_Delete_Employee_Record extends TestBase{

	    RequestSpecification httpRequest;
	    Response response;
		
		 Logger log4=LogManager.getLogger(TC005_Delete_Employee_Record.class);
		@BeforeClass
		void deleteEmployee() throws Exception {
			log4.info("---------Started TC005_Delete_Employee_Record--------");
			// Specify baseURI
			RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";

			// Create Request Object
			httpRequest = RestAssured.given();

			response=httpRequest.request(Method.GET,"/emplyees");
			
			//First get the JsonPath Object Instance from Response Interfeace
            JsonPath josonPathEvaluvator=response.jsonPath();
            
            //Capture ID
            String empID=josonPathEvaluvator.get("[0].id");
			httpRequest.header("Content-Type", "application/json");
		
			// Response Object
			response = httpRequest.request(Method.DELETE, "/delete/"+empID); 
			Thread.sleep(5000); // Waiting for response
		}
		
		@Test
		void check_responseBody()
		{
			
			String respBody=response.getBody().asString();
			Assert.assertEquals(respBody.contains("Successfully! Record has been deleted"),true);
		
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
			Assert.assertTrue(Integer.parseInt(contentLength)<0);//No data after deletion
		}
		@AfterClass
		void tearDown()
		{
			log4.info("---------Finished TC005_Delete_Employee_Record--------");
		}
}

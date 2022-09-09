package com.restassuredAutomation.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_GET_Autherization {

	@Test
	public void autherizationTest() {
		// Specify baseURI
		RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";

		// Basic Authentication
		PreemptiveBasicAuthScheme authSceme = new PreemptiveBasicAuthScheme();
		authSceme.setUserName("ToolsQA");
		authSceme.setPassword("TestPassword");

		RestAssured.authentication = authSceme;
		
		// Create Request Object
		RequestSpecification httprequest = RestAssured.given();

		// Response Object
		Response response = httprequest.request(Method.GET, "/");

		// Print Response in Console Window
		String respBody = response.getBody().asString();
		System.out.println("Response Body= " + respBody);

		// Status code Validation
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is= " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}
}

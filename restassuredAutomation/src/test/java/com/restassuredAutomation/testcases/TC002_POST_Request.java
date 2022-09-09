package com.restassuredAutomation.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_POST_Request {

	@Test
	void registrationSuccessful() {
		// Specify baseURI
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";

		// Create Request Object
		RequestSpecification httprequest = RestAssured.given();

		// Request payload Sending along with POST request
		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "JohnXYZ");
		requestParams.put("LastName", "XYZJohn");
		requestParams.put("UserName", "JohnXYZ");
		requestParams.put("Password", "JohnXYZxyz");
		requestParams.put("Email", "Johnxyz@gmail.com");

		httprequest.header("Content-Type", "application/json");
		httprequest.body(requestParams.toJSONString());

		// Response Object
		Response response = httprequest.request(Method.POST, "/register");

		// Print Response in Console Window
		String respBody = response.getBody().asString();
		System.out.println("Response Body= " + respBody);

		// Status code Validation
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is= " + statusCode);
		Assert.assertEquals(statusCode, 201);

		// Success code Validation
		String successCode = response.jsonPath().get("SuccessCode");
		Assert.assertEquals(successCode, "OPERATION SUCCESS");

	}
}

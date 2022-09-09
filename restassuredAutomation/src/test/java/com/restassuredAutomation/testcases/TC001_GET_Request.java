package com.restassuredAutomation.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GET_Request {

	@Test
	void getWeatherDetails() {
		// Specify baseURI
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// Create Request Object
		RequestSpecification httprequest = RestAssured.given();

		// Response Object
		Response response = httprequest.request(Method.GET, "/Hyderabad");

		// Print Response in Console Window
		String respBody = response.getBody().asString();
		System.out.println("Response Body= " + respBody);

		// Status code Validation
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is= " + statusCode);
		Assert.assertEquals(statusCode, 200);

		// Status line Verification
		String statusLine = response.statusLine();
		System.out.println("Status line is= " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}
}

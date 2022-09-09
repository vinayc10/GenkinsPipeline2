package com.restassuredAutomation.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_GET_PrintHeaders {

	@Test
	void googleMapTest() {
		// Specify baseURI
		RestAssured.baseURI = "https://maps.googleapis.com";

		// Create Request Object
		RequestSpecification httprequest = RestAssured.given();

		// Response Object
		Response response = httprequest.request(Method.GET, "/maps/api/place/nearbysearch/"
				+ "xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");

		// Print Response in Console Window
		String respBody = response.getBody().asString();
		System.out.println("Response Body= " + respBody);

		// validating Headers (Response headers)
		String contentType = response.header("Content-Type");
		System.out.println("Content Type= " + contentType);
		Assert.assertEquals(contentType, "application/xml; charset=UTF-8");

		String contentEncoding = response.header("Content-Encoding");
		System.out.println("Content Encoding= " + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");

		// Printing All Headers (Response headers)
		Headers allHeaders = response.headers();// capture all headers from response

		for (Header h : allHeaders) {
			System.out.println(h.getName() + "--> " + h.getValue());
		}

	}
}

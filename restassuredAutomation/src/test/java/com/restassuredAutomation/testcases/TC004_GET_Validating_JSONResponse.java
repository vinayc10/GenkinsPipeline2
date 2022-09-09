package com.restassuredAutomation.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_GET_Validating_JSONResponse {

	@Test
	public void getWeatherDetails() {
		// Specify baseURI
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// Create Request Object
		RequestSpecification httprequest = RestAssured.given();

		// Response Object
		Response response = httprequest.request(Method.GET, "/Delhi");

		// Print Response in Console Window
		String respBody = response.getBody().asString();
		System.out.println("Response Body= " + respBody);
		Assert.assertEquals(respBody.contains("Delhi"), true);

		// Now verify all contents of the Json body
		JsonPath js = response.jsonPath();// stores response content in Json format

		System.out.println(js.get("City"));
		System.out.println(js.get("Temperature"));
		System.out.println(js.get("Humidity"));
		System.out.println(js.get("WeatherDescription"));
		System.out.println(js.get("WindSpeed"));

	}
}

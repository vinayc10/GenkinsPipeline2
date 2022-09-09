package com.restassuredAutomation.testcases;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC006_POST_DataDriven_AddNewEmployee {
    
	@Test(dataProvider="employees")
	void postNewEmployee(String empName,String empSalary,String empAge)
	{
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		RequestSpecification httpRequest=RestAssured.given();
		
		JSONObject requestParam=new JSONObject();
		requestParam.put("name", empName);
		requestParam.put("salary", empSalary);
		requestParam.put("age", empAge);
		
		// Add header stating the request body is a JSON
		httpRequest.header("Content-Type","application/json");
		
		//Add JSON to the body of the Request
		httpRequest.body(requestParam.toJSONString());
		
		//POST request
		Response response=httpRequest.request(Method.POST,"/create");
		
		// Get response body from Response
		String resBody=response.getBody().asString();
		System.out.println("Response Body is: "+resBody);
		
		Assert.assertTrue(resBody.contains(empName));
		Assert.assertTrue(resBody.contains(empSalary));
		Assert.assertTrue(resBody.contains(empAge));
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@DataProvider(name="employees")
	String[][] getEmpData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/restassuredAutomation/empdata.xlsx";
		int rowNum=XLUtils.getRowCount(path, "Sheet1");
		int colCount=XLUtils.getCellCount(path, "Sheet1",1);// 1 means 1st row of Excel sheet
		
		String empData[][]=new String[rowNum][colCount];
		for(int i=1;i<=rowNum;i++)  //i=1 bcz Data available from 2nd row
		{
			for(int j=0;j<colCount;j++)
			{   
				//i-1   bcz start from 0th row
				empData[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return empData;
	}
	
	
}

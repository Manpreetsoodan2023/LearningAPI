package com.apiautomation.LearningAPI.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;


public class BestBuyAPI {
	
	@BeforeMethod
	public void setup() {
		baseURI = "http://localhost:3030";
	}
	

	@Test
	public void testGetProducts() {
		
		
		//Anything to do with request add it in given() and when()
		//then() holds the response and can invoke tests on response
		given().queryParam("$limit", 1).when().log().all().get("/products").then().log().all().statusCode(200).assertThat().body("data[0].price", equalTo(5.49F))
		.body("data[0].id", notNullValue());
		
	}
	
	
	@Test
	public void extractProductId(ITestContext context) {
		
		Response response = given().queryParam("$limit", 1).when().log().all().get("/products");
		
		int productId = response.body().jsonPath().getInt("data[0].id");
		System.out.println("ProductId " + productId );
		context.setAttribute("ProductId " , productId);
		
	}
	
	@Test
	public void testGetProductById(ITestContext context) {
		
		given().when().log().all().get("/products/{productId}", context.getAttribute("ProductId")).then().log().all().statusCode(200);
		
	}
	
	

}

package com.apiautomation.LearningAPI.tests;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class CreateIssueMetaDataTest {

	String ApiToken = "Basic bWFucHJlZXRzb29kYW44NUBnbWFpbC5jb206QVRBVFQzeEZmR0YwcHV6aVdWVFFxZVBFSkpBUHBaZ002SXhTa2p0OGg5QnVWRzZSMVlkbEhDNlA3VWxITC1ZVkJyVDVWNXBpNlVJLUI3ckV4bGM0Tm1SZVFtTEloYnBVSk90Y2owMlRnUWxjVU43cUhLeTVGdFJVU19qYkVUQVlnNlEwa0ZHQVVWVENWSkNfUUVud0JtX1VkdlFSX3p2LVAtRjdfMGxDS1U3U3JkVjY5OUQ3cE5BPTdCMTVFMTcw";

	@BeforeMethod
	public void setup() {

		baseURI = "https://manpreets.atlassian.net";

	}

	@Test(priority = 1)
	public void testApiCall() {

		given().header("Authorization", ApiToken).when().log().all().get("/rest/api/3/issue/createmeta").then().log()
				.all().statusCode(200);

	}

	@Test(priority = 2)
	public void testCreateIssue() throws FileNotFoundException {

		File f = new File(".\\body.json");
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);

		given().header("Authorization", ApiToken).contentType("application/json").body(data.toString()).when().log()
				.all().post("/rest/api/3/issue").then().log().all().statusCode(201);

	}

	@Test(priority = 3)
	public void extractIssueId(ITestContext context) throws FileNotFoundException {

		File f = new File(".\\body.json");
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);

		Response response = given().header("Authorization", ApiToken).contentType("application/json")
				.body(data.toString()).when().log().all().post("/rest/api/3/issue");

		int issueId = response.body().jsonPath().getInt("id");

		context.setAttribute("issueId", issueId);

	}

	@Test(priority = 4)
	public void testGetIssueId(ITestContext context) {

		given().header("Authorization", ApiToken).when().log().all()
				.get("/rest/api/3/issue/{issueId}", context.getAttribute("issueId")).then().log().all().statusCode(200);

	}

	@Test(priority = 5)
	public void testEditIssue(ITestContext context) {

		given().header("Authorization", ApiToken).contentType("application/json")
				.body("{\r\n" + "   \"update\": {\r\n" + "  \r\n" + "    \"summary\": [\r\n" + "      {\r\n"
						+ "        \"set\": \"this is updated summary\"\r\n" + "      }\r\n" + "    ]\r\n" + "\r\n"
						+ "  }\r\n" + "}")
				.when().log().all().put("/rest/api/3/issue/{issueId}", context.getAttribute("issueId")).then().log()
				.all().statusCode(204);

	}

	@Test(priority = 6)
	public void testDeleteIssue(ITestContext context) {

		given().header("Authorization", ApiToken).when().log().all()
				.delete("/rest/api/3/issue/{issueId}", context.getAttribute("issueId")).then().log().all()
				.statusCode(204);

	}

}

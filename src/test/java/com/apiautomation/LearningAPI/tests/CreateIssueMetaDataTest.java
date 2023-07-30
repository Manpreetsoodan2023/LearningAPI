package com.apiautomation.LearningAPI.tests;

import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CreateIssueMetaDataTest {

	@BeforeMethod
	public void setup() {

		baseURI = "https://manpreets.atlassian.net";

	}

	@Test(priority = 1)
	public void testApiCall() {

		given().header("Authorization",
				"Basic bWFucHJlZXRzb29kYW44NUBnbWFpbC5jb206QVRBVFQzeEZmR0YwcHV6aVdWVFFxZVBFSkpBUHBaZ002SXhTa2p0OGg5QnVWRzZSMVlkbEhDNlA3VWxITC1ZVkJyVDVWNXBpNlVJLUI3ckV4bGM0Tm1SZVFtTEloYnBVSk90Y2owMlRnUWxjVU43cUhLeTVGdFJVU19qYkVUQVlnNlEwa0ZHQVVWVENWSkNfUUVud0JtX1VkdlFSX3p2LVAtRjdfMGxDS1U3U3JkVjY5OUQ3cE5BPTdCMTVFMTcw")
				.when().log().all().get("/rest/api/3/issue/createmeta").then().log().all().statusCode(200);

	}

	@Test(priority = 2)
	public void testCreateIssue() {

		given().header("Authorization",
				"Basic bWFucHJlZXRzb29kYW44NUBnbWFpbC5jb206QVRBVFQzeEZmR0YwcHV6aVdWVFFxZVBFSkpBUHBaZ002SXhTa2p0OGg5QnVWRzZSMVlkbEhDNlA3VWxITC1ZVkJyVDVWNXBpNlVJLUI3ckV4bGM0Tm1SZVFtTEloYnBVSk90Y2owMlRnUWxjVU43cUhLeTVGdFJVU19qYkVUQVlnNlEwa0ZHQVVWVENWSkNfUUVud0JtX1VkdlFSX3p2LVAtRjdfMGxDS1U3U3JkVjY5OUQ3cE5BPTdCMTVFMTcw")
				.contentType("application/json")
				.body("{\r\n" + "  \"fields\": {\r\n" + "    \r\n" + "    \"description\": {\r\n"
						+ "      \"content\": [\r\n" + "        {\r\n" + "          \"content\": [\r\n"
						+ "            {\r\n" + "              \"text\": \"Creating report funtionality test.\",\r\n"
						+ "              \"type\": \"text\"\r\n" + "            }\r\n" + "          ],\r\n"
						+ "          \"type\": \"paragraph\"\r\n" + "        }\r\n" + "      ],\r\n"
						+ "      \"type\": \"doc\",\r\n" + "      \"version\": 1\r\n" + "    },\r\n"
						+ "    \"issuetype\": {\r\n" + "      \"id\": \"10001\"\r\n" + "    },\r\n"
						+ "    \"project\": {\r\n" + "      \"id\": \"10000\"\r\n" + "    },\r\n"
						+ "    \"summary\": \"Report functionality test\"\r\n" + "  }\r\n" + "}")
				.when().log().all().post("/rest/api/3/issue").then().log().all().statusCode(201);

	}

	@Test(priority = 3)
	public void extractIssueId(ITestContext context) {

		Response response = given().header("Authorization",
				"Basic bWFucHJlZXRzb29kYW44NUBnbWFpbC5jb206QVRBVFQzeEZmR0YwcHV6aVdWVFFxZVBFSkpBUHBaZ002SXhTa2p0OGg5QnVWRzZSMVlkbEhDNlA3VWxITC1ZVkJyVDVWNXBpNlVJLUI3ckV4bGM0Tm1SZVFtTEloYnBVSk90Y2owMlRnUWxjVU43cUhLeTVGdFJVU19qYkVUQVlnNlEwa0ZHQVVWVENWSkNfUUVud0JtX1VkdlFSX3p2LVAtRjdfMGxDS1U3U3JkVjY5OUQ3cE5BPTdCMTVFMTcw")
				.contentType("application/json")
				.body("{\r\n" + "  \"fields\": {\r\n" + "    \r\n" + "    \"description\": {\r\n"
						+ "      \"content\": [\r\n" + "        {\r\n" + "          \"content\": [\r\n"
						+ "            {\r\n" + "              \"text\": \"Creating report funtionality test.\",\r\n"
						+ "              \"type\": \"text\"\r\n" + "            }\r\n" + "          ],\r\n"
						+ "          \"type\": \"paragraph\"\r\n" + "        }\r\n" + "      ],\r\n"
						+ "      \"type\": \"doc\",\r\n" + "      \"version\": 1\r\n" + "    },\r\n"
						+ "    \"issuetype\": {\r\n" + "      \"id\": \"10001\"\r\n" + "    },\r\n"
						+ "    \"project\": {\r\n" + "      \"id\": \"10000\"\r\n" + "    },\r\n"
						+ "    \"summary\": \"Report functionality test\"\r\n" + "  }\r\n" + "}")
				.when().log().all().post("/rest/api/3/issue");

		int issueId = response.body().jsonPath().getInt("id");

		context.setAttribute("issueId", issueId);

	}

	@Test(priority = 4)
	public void testGetIssueId(ITestContext context) {

		given().header("Authorization",
				"Basic bWFucHJlZXRzb29kYW44NUBnbWFpbC5jb206QVRBVFQzeEZmR0YwcHV6aVdWVFFxZVBFSkpBUHBaZ002SXhTa2p0OGg5QnVWRzZSMVlkbEhDNlA3VWxITC1ZVkJyVDVWNXBpNlVJLUI3ckV4bGM0Tm1SZVFtTEloYnBVSk90Y2owMlRnUWxjVU43cUhLeTVGdFJVU19qYkVUQVlnNlEwa0ZHQVVWVENWSkNfUUVud0JtX1VkdlFSX3p2LVAtRjdfMGxDS1U3U3JkVjY5OUQ3cE5BPTdCMTVFMTcw")
				.when().log().all().get("/rest/api/3/issue/{issueId}", context.getAttribute("issueId")).then().log()
				.all().statusCode(200);

	}

	@Test(priority = 5)
	public void testEditIssue(ITestContext context) {

		given().header("Authorization",
				"Basic bWFucHJlZXRzb29kYW44NUBnbWFpbC5jb206QVRBVFQzeEZmR0YwcHV6aVdWVFFxZVBFSkpBUHBaZ002SXhTa2p0OGg5QnVWRzZSMVlkbEhDNlA3VWxITC1ZVkJyVDVWNXBpNlVJLUI3ckV4bGM0Tm1SZVFtTEloYnBVSk90Y2owMlRnUWxjVU43cUhLeTVGdFJVU19qYkVUQVlnNlEwa0ZHQVVWVENWSkNfUUVud0JtX1VkdlFSX3p2LVAtRjdfMGxDS1U3U3JkVjY5OUQ3cE5BPTdCMTVFMTcw")
				.contentType("application/json")
				.body("{\r\n" + "   \"update\": {\r\n" + "  \r\n" + "    \"summary\": [\r\n" + "      {\r\n"
						+ "        \"set\": \"this is updated summary\"\r\n" + "      }\r\n" + "    ]\r\n" + "\r\n"
						+ "  }\r\n" + "}")
				.when().log().all().put("/rest/api/3/issue/{issueId}", context.getAttribute("issueId")).then().log()
				.all().statusCode(204);

	}

	@Test(priority = 6)
	public void testDeleteIssue(ITestContext context) {

		given().header("Authorization",
				"Basic bWFucHJlZXRzb29kYW44NUBnbWFpbC5jb206QVRBVFQzeEZmR0YwcHV6aVdWVFFxZVBFSkpBUHBaZ002SXhTa2p0OGg5QnVWRzZSMVlkbEhDNlA3VWxITC1ZVkJyVDVWNXBpNlVJLUI3ckV4bGM0Tm1SZVFtTEloYnBVSk90Y2owMlRnUWxjVU43cUhLeTVGdFJVU19qYkVUQVlnNlEwa0ZHQVVWVENWSkNfUUVud0JtX1VkdlFSX3p2LVAtRjdfMGxDS1U3U3JkVjY5OUQ3cE5BPTdCMTVFMTcw")
				.when().log().all().delete("/rest/api/3/issue/{issueId}", context.getAttribute("issueId")).then().log()
				.all().statusCode(204);

	}

}

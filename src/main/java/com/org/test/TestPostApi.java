package com.org.test;

import java.io.File;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.base.TestBase;
import com.org.client.RestClient;
import com.org.data.Users;

public class TestPostApi extends TestBase {
	
	TestBase testBase;
	String serviceURL;
	String apiURL;
	String url;
	RestClient restClient;
	CloseableHttpResponse  closableHttpResponse;
	
	
	@BeforeMethod
	public void setUp()throws Exception {
		
		testBase=new TestBase();
		
		serviceURL=prop.getProperty("url");
		apiURL=prop.getProperty("serviceURL");
		url=serviceURL+apiURL;
		
		}
	
	@Test
	public void testPostRequest()throws Exception {
		restClient=new RestClient();
		HashMap<String,String> headerMap=new HashMap();
		headerMap.put("Content-Type","application/json");
		//JAckson API
		//Marshalling converting java to jason
		ObjectMapper objectMapper=new ObjectMapper();
		Users users=new Users("morpheus","leader");
		
		objectMapper.writeValue(new File(System.getProperty("user.dir")+"/src/main/java/com/org/data/users.json"), users);
		String jasonObjectAsString=objectMapper.writeValueAsString(users);
		
		System.out.println(jasonObjectAsString);
		
		closableHttpResponse=restClient.post(url,jasonObjectAsString,headerMap);
		
		//status code
		
		int statusCode=closableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.REST_STATUS_CODE_201);
		
		//validating the entity
		String jsonString=EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");
		System.out.println(jsonString);
		
		Users jsonUser=objectMapper.readValue(jsonString, Users.class);
		Assert.assertEquals(users.getName(), jsonUser.getName());
		Assert.assertEquals(users.getJob(), jsonUser.getJob());
		}
	
	
	

}

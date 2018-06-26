package com.org.test;

import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.org.base.TestBase;
import com.org.client.RestClient;
import com.org.util.TestUtils;

public class TestApi extends TestBase{
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
	public void getApiTest() throws Exception{
		
		restClient=new RestClient();
		closableHttpResponse=restClient.get(url);
		
		//a.status code
		int statusCode=closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Get Status Code--->"+statusCode);
		
		Assert.assertEquals( statusCode,REST_STATUS_CODE_200,"ststus code is incorrecr");
		//response
		String responseString=EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");
		
		JSONObject jasonObject=new JSONObject(responseString);
		
		System.out.println("Response API--->"+jasonObject);
		
		String perPageValue=TestUtils.getValueByJPath(jasonObject, "per_page");
		Assert.assertEquals(perPageValue, "3");
		
		String totalValue=TestUtils.getValueByJPath(jasonObject, "total");
		Assert.assertEquals(totalValue, "12");
		//validating the data array
		String id=TestUtils.getValueByJPath(jasonObject,"data[0]/id");
		String first_name=TestUtils.getValueByJPath(jasonObject,"data[0]/first_name");
		String last_name=TestUtils.getValueByJPath(jasonObject,"data[0]/last_name");
		String avatar=TestUtils.getValueByJPath(jasonObject,"data[0]/avatar");
		
		System.out.println("id"+id);
		System.out.println("first_name"+first_name);
		System.out.println("last_name"+last_name);
		System.out.println("avatar"+avatar);
		
		
		//validating headers
		
		Header[] headers=closableHttpResponse.getAllHeaders();
		
		HashMap <String,String> allHeaders=new HashMap();
		
		for(Header header:headers){
			
			allHeaders.put(header.getName(), header.getValue());
			
			
		}
		System.out.println("All headers--->"+allHeaders);
		String server=allHeaders.get("Server");
		System.out.println("server"+server);
		
	}
	
	
	@Test
	public void getApiTestWithHeader() throws Exception{
		
		restClient=new RestClient();
		HashMap<String,String> hashMap=new HashMap();
		hashMap.put("Content-Type","application/json");
		closableHttpResponse=restClient.get(url,hashMap);
		
		//a.status code
		int statusCode=closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Get Status Code--->"+statusCode);
		
		Assert.assertEquals( statusCode,REST_STATUS_CODE_200,"ststus code is incorrecr");
		//response
		String responseString=EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");
		
		JSONObject jasonObject=new JSONObject(responseString);
		
		System.out.println("Response API--->"+jasonObject);
		
		String perPageValue=TestUtils.getValueByJPath(jasonObject, "per_page");
		Assert.assertEquals(perPageValue, "3");
		
		String totalValue=TestUtils.getValueByJPath(jasonObject, "total");
		Assert.assertEquals(totalValue, "12");
		//validating the data array
		String id=TestUtils.getValueByJPath(jasonObject,"data[0]/id");
		String first_name=TestUtils.getValueByJPath(jasonObject,"data[0]/first_name");
		String last_name=TestUtils.getValueByJPath(jasonObject,"data[0]/last_name");
		String avatar=TestUtils.getValueByJPath(jasonObject,"data[0]/avatar");
		
		System.out.println("id"+id);
		System.out.println("first_name"+first_name);
		System.out.println("last_name"+last_name);
		System.out.println("avatar"+avatar);
		
		
		//validating headers
		
		Header[] headers=closableHttpResponse.getAllHeaders();
		
		HashMap <String,String> allHeaders=new HashMap();
		
		for(Header header:headers){
			
			allHeaders.put(header.getName(), header.getValue());
			
			
		}
		System.out.println("All headers--->"+allHeaders);
		String server=allHeaders.get("Server");
		System.out.println("server"+server);
		
	}

}

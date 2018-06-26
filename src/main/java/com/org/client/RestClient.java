package com.org.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
//Get method with headers	
	public CloseableHttpResponse get(String url) throws Exception{
		CloseableHttpClient closablrHTTPClient=HttpClients.createDefault();
		HttpGet httpGet=new HttpGet(url);
		CloseableHttpResponse closableHttpResponse=closablrHTTPClient.execute(httpGet);
		
		
		return closableHttpResponse;
		
	}
//get method without headers	
	public CloseableHttpResponse get(String url,HashMap<String,String> hashmap) throws Exception{
		CloseableHttpClient closablrHTTPClient=HttpClients.createDefault();
		HttpGet httpGet=new HttpGet(url);
		
		
		for(Map.Entry<String,String> entry:hashmap.entrySet()) {
			httpGet.addHeader(entry.getKey(), entry.getValue());
			
		}
		
		CloseableHttpResponse closableHttpResponse=closablrHTTPClient.execute(httpGet);
		return closableHttpResponse;
		
	}
	
	//post method with headers	
	public CloseableHttpResponse post(String url,String entity,HashMap<String,String> hashmap) throws Exception{
		CloseableHttpClient closablrHTTPClient=HttpClients.createDefault();
		HttpPost httpPost=new HttpPost(url);
		
		httpPost.setEntity(new StringEntity(entity));
		
		
		for(Map.Entry<String,String> entry:hashmap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
			
		}
		
		CloseableHttpResponse closableHttpResponse=closablrHTTPClient.execute(httpPost);
		return closableHttpResponse;
		
	}
	


}

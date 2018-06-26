package com.org.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	public Properties prop;
	public int REST_STATUS_CODE_200=200;
	public int REST_STATUS_CODE_201=201;
	public TestBase() {
		
		try{prop=new Properties();
		FileInputStream fileInputStream =new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/org/config/config.properties");
		prop.load(fileInputStream);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
			
			
		}catch(IOException ie) {
			
		  ie.printStackTrace();
		}
		
		
	}
	
	
}

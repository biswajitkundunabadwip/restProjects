package com.biswajit.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class TestBase {

	RestAssured restAssured;
	FileInputStream fis;
	Properties prop;
	static JsonPath js;
	
	public void initPropFile(FileInputStream fis) throws IOException {
		prop= new Properties();
		prop.load(fis);
	}
	@BeforeClass
	public void init() throws FileNotFoundException {
		fis= new FileInputStream(Path.CONFIG_PATH);
		try {
			initPropFile(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		restAssured.baseURI = prop.getProperty("baseURI");
	}
	
	public static JsonPath jsonPathExtracTor(String response) {
		js = new JsonPath(response);
		return js;
	}
}

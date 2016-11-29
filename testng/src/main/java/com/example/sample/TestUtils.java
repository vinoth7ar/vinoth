package com.example.sample;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;

public class TestUtils {

	public static HttpURLConnection httpURLConnection;

	public String processRequestForService(String requestType, String request, int expRespCode,
			HttpURLConnection httpURLConnection) {
		String result = null;
		BufferedReader reader = null;
		try {
			if (httpURLConnection != null) {

				if (requestType.contains("GET")) {
					httpURLConnection.setRequestMethod(requestType);
				}
				if (requestType.contains("POST")) {
					httpURLConnection.setRequestMethod(requestType);
					httpURLConnection.setRequestProperty("Content-Type", "application/json");
					DataOutputStream out = new DataOutputStream(httpURLConnection.getOutputStream());
					out.writeBytes(IOUtils.toString(new FileReader(request)));
				}
				httpURLConnection.connect();
				reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
				String line;
				while ((line = reader.readLine()) != null) {
					result += line;
				}
				Assert.assertTrue(expRespCode == httpURLConnection.getResponseCode(),
						"Expected Response code 200 OK,and Actual Response Code::"
								+ httpURLConnection.getResponseCode());
			}
			reader.close();
		} catch (IOException ioException) {
			Assert.fail(ioException.toString());
		}
		if (result.contains("null")) {
			result = result.replace("null", "");
		}
		return result;
	}

	public JSONObject convertStringToJsonObject(String response) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = new JSONObject();
		Object obj;
		try {
			obj = parser.parse(response);
			jsonObject = (JSONObject) obj;
		} catch (Exception parseException) {
		}
		return jsonObject;
	}

	public static void main(String args[]) {
		TestUtils testUtils = new TestUtils();
		String jsonResponse = testUtils.processRequestForService("GET", null, 200,
				startGetConnection("http://services.groupkt.com/country/get/all"));
		System.out.println(jsonResponse);
	}

	private static HttpURLConnection startGetConnection(String targetURL) {
		try {
			URL url = new URL(targetURL);
			URLConnection urlConnection;
			urlConnection = url.openConnection();
			httpURLConnection = (HttpURLConnection) urlConnection;
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		return httpURLConnection;
	}
}

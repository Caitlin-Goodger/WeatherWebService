package example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.jws.WebService;

import org.json.JSONArray;
import org.json.JSONObject;

@WebService (
			endpointInterface = "example.WebServiceInterface",
			portName = "webservicePort",
			serviceName = "WebService"
		)
public class WebServiceImpl implements WebServiceInterface {
	//Head of the url
	private String head = "https://api.openweathermap.org/data/2.5/weather?q=";
	//End of the url
	private String end = "&appid=75ad0017682564acd8ff0cf120e83f51";
	//Set the units to metric
	private String metric = "&units=metric";
	
	@Override
	public String greet(String clientName) {
		// TODO Auto-generated method stub
		return "Hi, " + clientName + ", this is WebService!";
	}
	
	/**
	 * Get the weather from openweather based on the city entered
	 * It uses JSON to get the information
	 */
	@Override
	public String getWeather(String city) throws Exception {	
		String link = head + city + metric + end;
		String json = readURL(link);
		JSONObject jObj = new JSONObject(json);
		
		Double temp = jObj.getJSONObject("main").getDouble("temp");
		JSONArray arr = jObj.getJSONArray("weather");
		String descript = arr.getJSONObject(0).getString("description");
		
		return "The tempature is " + temp + " and " + descript;
	}
	
	/**
	 * Randomly picks a temperature between -10 and 50
	 */
	@Override
	public double weather(String city, String country) {
		double temp = Math.random() * 50 -10;
		temp = temp*100;
		temp = Math.round(temp);
		temp = temp/100;
		return temp;
	}
	
	/**
	 * Read the string of the link and convert it to something that JSON can read
	 * @param urlString
	 * @return
	 * @throws Exception
	 */
	public String readURL(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
}

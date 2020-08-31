package example;

import javax.jws.WebService;

@WebService
public interface WebServiceInterface {
	
	public String greet(String clientName);

	double weather(String city, String country);

	String getWeather(String city) throws Exception;
	
}

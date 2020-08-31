package example;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class WebServiceClient {
	
	public static void main(String [] args) throws MalformedURLException {
		URL url = new URL("http://localhost:8080/MyWebService/WebServiceInterface?wsdl");
		QName qname = new QName("http://example/", "WebService");
		Service service = Service.create(url,qname);
		WebServiceInterface callWebService = service.getPort(WebServiceInterface.class);
		System.out.println("Serviceoutput:\n" + callWebService.greet("WebServiceClient"));
		Scanner scan = new Scanner(System.in);
		System.out.print("What city are you in?");
		String city = scan.nextLine();
		//Code to randomly give a temperature
//		System.out.print("What country are you in?");
//		String country = scan.nextLine();
//		System.out.println("The temperature in " + city + ", "+ country + " is " + callWebService.weather(city,country));
//		
		//Get the weather from openweather
		try {
			System.out.println(callWebService.getWeather(city));
		} catch (Exception e) {
			System.out.println("Please enter a valid city");
		}
		
		
	}
}

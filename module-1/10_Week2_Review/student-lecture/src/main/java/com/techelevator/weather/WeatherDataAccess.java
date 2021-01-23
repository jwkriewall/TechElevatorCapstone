package com.techelevator.weather;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherDataAccess {
	
	public Map<String, List<Weather>> getWeatherForCity(){
		
		//Create a new HashMap for the forecasts of cities
		// Creates a list of weather where each item represents a day (cityWeather variables)
		// Add the list to the cityForecast Map
		// Repeat for each city!
		// After all cities are added return the map
		
		Map <String, List<Weather>> cityForecast = new HashMap<String, List<Weather>>();
		
		
		List<Weather> cityWeather = new ArrayList<Weather>();
		
		
		//Using the constructor
							// day of week, temp, forecast
		Weather weather = new Weather(1, 35, "Sunny");
		cityWeather.add(weather);
		
		// or set value using setters
		weather = new Weather(2);
		weather.setForecast("Cloudy");
		weather.setTempInFahrenheit(43);
		
		cityWeather.add(new Weather(3, 20, "Snowy"));
		cityWeather.add(new Weather(4, 1, "Chilly"));
		cityWeather.add(new Weather(5, 100, "Hot"));
		cityWeather.add(new Weather(6, 21, "Snowy"));
		cityWeather.add(new Weather(7, 40, "Cloudy"));
		cityWeather.add(new Weather(2, 24, "Cold"));
		
		
		
		cityForecast.put("COLUMBUS", cityWeather);
		
		
		// instantiate a new list for Cleveland
		// cityWeather variable is refreshed with the new ArrayList
		// now cityWeather can hold new inputs under a different name
		cityWeather = new ArrayList<Weather>();
		
		cityWeather.add(new Weather(3, 20, "Windy"));
		cityWeather.add(new Weather(4, 14, "Windy"));
		cityWeather.add(new Weather(5, 10, "Windy"));
		cityWeather.add(new Weather(6, 2, "Windy"));
		cityWeather.add(new Weather(7, 4, "OUCHIE"));
		cityWeather.add(new Weather(2, -4, "YIKERS"));
		
		cityForecast.put("CLEVELAND", cityWeather);
		
		
		
		return cityForecast;
	}
	//System.out.println(cityForecast[1]);

}

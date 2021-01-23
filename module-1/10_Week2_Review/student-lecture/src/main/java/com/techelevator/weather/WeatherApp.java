package com.techelevator.weather;

import java.util.Map;
import java.util.Scanner;

import java.util.List;

public class WeatherApp {
	
	private final static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		
		
		
		// Ask user what city
		// Get the forecast for that city
		// display the forecast

				
		//Instantiate (Create) a weatherDataAccess object
		WeatherDataAccess weatherData = new WeatherDataAccess();
		
		// Using the WeatherDataAccess object to retrieve the map of the weather for different cities
		Map<String, List<Weather>> cityForecasts = weatherData.getWeatherForCity();
		
		System.out.print("What City (Columbus or Cleveland) >>> ");
		String citySelected = input.nextLine();
		
		System.out.print("(F))ahenheit or (C)elcius >>> ");
		String tempScaleSelected = input.nextLine();
		
		List<Weather> forecast = cityForecasts.get(citySelected.toUpperCase());
		
		System.out.println();
		System.out.println("The forecast for " + citySelected);
		
		for (Weather day : forecast) {
			System.out.print(day.getDayOfWeek() + " - ");
			int temp = day.getTempInFahrenheit();
			if (tempScaleSelected.equalsIgnoreCase("C")) {
				temp = Weather.convertFahrenheitToCelcius(temp);
				
			}
			
			System.out.print(temp + tempScaleSelected + " - ");
			System.out.println(day.getForecast());
		}
	}

}

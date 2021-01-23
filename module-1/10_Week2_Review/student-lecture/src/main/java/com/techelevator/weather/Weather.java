package com.techelevator.weather;

public class Weather {
	
	private final static String[] DAYS_OF_THE_WEEK = 
		{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	
	private static final int DEFAULT_DAY = 1;
	
	// state (data - member variables/instance variables AKA private variables that hold the state)
	// state is the properties. There may be a default, but the state CAN change.
	
	private int dayOfWeek;
	private int tempInFahrenheit;
	private String forecast;
	
	// all these change so we will need to make getters and setters
	// getters and setters provide public access to private member variables
	
	public int getTempInFahrenheit() {
		return this.tempInFahrenheit;
		
	}
	
	public void setTempInFahrenheit(int tempInFahrenheit) {
		this.tempInFahrenheit = tempInFahrenheit;
	}
	
	public String getForecast() {
		return this.forecast;
	}
	
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	
	
	
	// constructor - How to instantiate the class
	
	public Weather() {} // this is an implied constructor
	
	public Weather(int day) {
		this.dayOfWeek = day;
	}
	
	public Weather(int day, int tempInFahrenheit, String forecast) {
		validateDayOfWeek(day);
		this.tempInFahrenheit = tempInFahrenheit;
		this.forecast = forecast;
	}
	
	// Derived property (getter) doesn't store a value, but calculates
	public String getDayOfWeek() {
		return DAYS_OF_THE_WEEK[this.dayOfWeek - 1];
	}

	
	// behavior (methods - how this class can change)
	
	private void validateDayOfWeek(int day) {
		if (day < 1 || day > 7) {
			this.dayOfWeek = DEFAULT_DAY;
		}
		else {
			this.dayOfWeek = day;
		}
	}
	
	public static int convertFahrenheitToCelcius(int fahrenheit) {
		return ((fahrenheit - 30) / 2);
	}
	

}

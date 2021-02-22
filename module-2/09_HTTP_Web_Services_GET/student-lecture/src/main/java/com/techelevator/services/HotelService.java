package com.techelevator.services;

import com.techelevator.models.City;
import com.techelevator.models.Hotel;
import com.techelevator.models.Review;
import org.springframework.web.client.RestTemplate;

public class HotelService {

    private final String API_BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();

    public HotelService(String apiURL) {
        API_BASE_URL = apiURL;
    }

    public Hotel[] listHotels() {
    	
    	String endpointURL = API_BASE_URL + "hotels";
    	//String rawResponse = restTemplate.getForObject(endpointURL, String.class);
    	
    	Hotel[] hotels = restTemplate.getForObject(endpointURL, Hotel[].class);
    	// .class references data type we want
    	// we send data type of what we want to JSON 
    	
    	return hotels;
    }

    public Review[] listReviews() {
    	
    	Review[] reviews = restTemplate.getForObject(API_BASE_URL + "reviews", Review[].class);
    	return reviews;
    }

    public Hotel getHotelById(int id) {
    	
    	String endpointURL = API_BASE_URL + "hotels/" + id;
    	Hotel hotel = restTemplate.getForObject(endpointURL, Hotel.class);
    	return hotel;
    }

    public Review[] getReviewsByHotelId(int hotelID) {
    	
    	String endpointUrl = API_BASE_URL + "hotels/" + hotelID + "/reviews";
    	return restTemplate.getForObject(endpointUrl, Review[].class);
    }

    public Hotel[] getHotelsByStarRating(int stars) {
    	
    	String endpointUrl = API_BASE_URL + "hotels?stars=" + stars;
    	return restTemplate.getForObject(endpointUrl, Hotel[].class);
    }

    public City getWithCustomQuery(){
    	String endpointUrl = "https://api.teleport.org/api/cities/geonameid:5128581/";
    	City city = restTemplate.getForObject(endpointUrl, City.class);
    	return city;
    }

}

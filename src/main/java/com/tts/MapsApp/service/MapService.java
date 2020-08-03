package com.tts.MapsApp.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tts.MapsApp.model.GeocodingResponse;
import com.tts.MapsApp.model.Location;

@Service
public class MapService {

	@Value("${api_key}")
	private String apiKey;
//This will generate random INT and DOUBLES
	Random rand = new Random();
//Our latitudes are +-85 because the latitude of the google map doesn't include Artic
	private int latMin = -85;
	private int latMax = 85;
	private int lngMin = -180;
	private int lngMax = 180;

//Pass through random int, return coords String
	public String genRandString(int dl) {
		int test = rand.nextInt(2);
		int a = dl;
		//Test is a random between 0 and 1, which is positive and negative respectively
		if (test == 0) {
			a = a * 1;
		} else { a = a * -1; }
		
		
		double b = rand.nextDouble();
		// Adds rand double to rand Int
		double x = a + b;

		String str = String.valueOf(x);

		return str;
	}

	// This method looks for coordinates of a city + state
	public void addCoordinates(Location location) {
		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + location.getCity() + ","
				+ location.getState() + "&key=" + apiKey;

		RestTemplate restTemplate = new RestTemplate();
		GeocodingResponse response = restTemplate.getForObject(url, GeocodingResponse.class);
		Location coordinates = response.getResults().get(0).getGeometry().getLocation();

		location.setLat(coordinates.getLat());
		location.setLng(coordinates.getLng());
	}

	// This method looks for random coordinates
	public void addRandom(Location location) {
		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + location.getCity() + ","
				+ location.getState() + "&key=" + apiKey;

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject(url, GeocodingResponse.class);

		location.setLat(genRandString(rand.nextInt((latMax - latMin + 1) + latMin)));
		location.setLng(genRandString(rand.nextInt((lngMax - lngMin + 1) + lngMin)));
	}
}

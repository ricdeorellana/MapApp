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
	
		Random rand = new Random();
		

		
		public String genRandString(Double dl) {
			double x = dl;
		
			for (int i = 1; i<=2; i++) {
				x = x/.1;
			}
			String str = String.valueOf(x);
			
			return str;
			
		}
		
		
		public void addCoordinates(Location location) {
			String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + 
				    location.getCity() + "," + location.getState() + "&key=" + apiKey;
			
			RestTemplate restTemplate = new RestTemplate();
			GeocodingResponse response = restTemplate.getForObject(url,  GeocodingResponse.class);
			Location coordinates = response.getResults().get(0).getGeometry().getLocation();
			
			location.setLat(coordinates.getLat());
			location.setLng(coordinates.getLng());
		}
		
		public void addRandom(Location location) {
			String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + 
				    location.getCity() + "," + location.getState() + "&key=" + apiKey;
			
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getForObject(url,  GeocodingResponse.class);
			
			location.setLat(genRandString(rand.nextDouble()));
			location.setLng(genRandString(rand.nextDouble()));
		}
}

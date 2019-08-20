package com.weatherapp.WeatherApp.api.location;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.weatherapp.WeatherApp.api.dto.LocationDto;

  @Service
@RestController
public class LocationAPI {
	@Value("${api.key}")
	String apiKey;
	
	String endpoint = "http://dataservice.accuweather.com/locations/v1/cities/autocomplete?apikey=";
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/location")
	public LocationDto getLocation(@RequestParam("text") String text){
		ResponseEntity<List<LocationDto>> response = restTemplate.exchange(
			endpoint + text,
			HttpMethod.GET,
			null,
			new ParameterizedTypeReference<List<LocationDto>>(){});
		List<LocationDto> locations = response.getBody();
		if(locations.isEmpty()) {
			throw new RuntimeException("Location not found");
		}
		return locations.get(0);
	}
	
	@GetMapping("/location/key")
	public String getLocationKey(@RequestParam("text") String text){
		return getLocation(text).key;
	}
	
	@PostConstruct
	public void constructEndpointAdress() {
		this.endpoint += apiKey + "&q=";
	}
}

package com.weatherapp.WeatherApp.api.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.weatherapp.WeatherApp.api.dto.CurrentConditionsDto;
import com.weatherapp.WeatherApp.repo.UserRepo;

  @Service
@RestController
public class LocationCurrentConditionsAPI {
	  
	@Value("${api.key}")
	String apiKey;
	
	String endpoint = "http://dataservice.accuweather.com/currentconditions/v1/";
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	LocationAPI locationApi;
	
	@Autowired
	UserRepo userRepo;
	
	@GetMapping("/temperature")
	public CurrentConditionsDto getCurrentConditionForCityName(@RequestParam("cityName") String cityName) {
		System.out.println("key" + apiKey);
		String key = locationApi.getLocationKey(cityName);
		System.out.println(key);
		return this.getCurrentConditionForLocationKey(key);
	} 
	
	public CurrentConditionsDto getCurrentConditionForLocationKey(@RequestParam("locationKey") String locationKey){
		ResponseEntity<List<CurrentConditionsDto>> response = restTemplate.exchange(
			endpoint + locationKey + "/?apikey=" + apiKey + "&details=true",
			HttpMethod.GET,
			null,
			new ParameterizedTypeReference<List<CurrentConditionsDto>>(){});
		List<CurrentConditionsDto> data = response.getBody();
		if(data.isEmpty()) {
			throw new RuntimeException("Current conditions for locationkey=" + locationKey + " not found");
		}
		return data.get(0);
	}
	
	@GetMapping("/conditions/current/user")
	public CurrentConditionsDto getCurrentConditionsForCurrentUser() {
		String country = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getCountry();
		CurrentConditionsDto currentConditionsDto = getCurrentConditionForCityName(country);
		if(currentConditionsDto != null) {
			return currentConditionsDto;
		}
		throw new IllegalArgumentException("There is no current conditions for country: " + country);
	}
	
}

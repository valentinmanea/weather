package com.weatherapp.WeatherApp.api.location;

import java.util.List;

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

import com.weatherapp.WeatherApp.api.dto.CurrentConditionsDto;

  @Service
@RestController
public class LocationCurrentConditionsAPI {
	  
	@Value("${api.key}")
	String apiKey;
	
	String endpoint = "http://dataservice.accuweather.com/currentconditions/v1/";
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/temperature")
	public CurrentConditionsDto getLocation(@RequestParam("locationKey") String locationKey){
		ResponseEntity<List<CurrentConditionsDto>> response = restTemplate.exchange(
			endpoint + locationKey + "/?apikey=" + apiKey,
			HttpMethod.GET,
			null,
			new ParameterizedTypeReference<List<CurrentConditionsDto>>(){});
		List<CurrentConditionsDto> data = response.getBody();
		if(data.isEmpty()) {
			throw new RuntimeException("Current conditions for locationkey=" + locationKey + " not found");
		}
		return data.get(0);
	}
	
	
}

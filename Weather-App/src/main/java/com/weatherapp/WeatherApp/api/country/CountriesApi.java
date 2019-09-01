package com.weatherapp.WeatherApp.api.country;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.weatherapp.WeatherApp.api.country.dto.CountryDto;

@RestController
public class CountriesApi {
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/country/all")
	public List<String> getAllCountriesName(){
		String endpoint ="https://restcountries.eu/rest/v2/all";
		ResponseEntity<List<CountryDto>> response = restTemplate.exchange(
				endpoint,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<CountryDto>>(){});
		return response.getBody().stream().map(country -> country.name).collect(Collectors.toList());
	}
	
}

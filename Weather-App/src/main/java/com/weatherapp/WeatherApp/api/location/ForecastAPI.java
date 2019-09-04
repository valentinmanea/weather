package com.weatherapp.WeatherApp.api.location;

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

import com.weatherapp.WeatherApp.api.dto.ForecastDto;

  @Service
@RestController
public class ForecastAPI {
	  @Value("${api.key}")
		String apiKey;
		
		String endpoint = "http://dataservice.accuweather.com/forecasts/v1/daily/1day/";
		@Autowired
		RestTemplate restTemplate;
		
		@Autowired
		LocationAPI locationApi;
		
		@GetMapping("/forecast")
		public ForecastDto getForecastByLocationKey(@RequestParam("locationKey") String locationKey){
			ResponseEntity<ForecastDto> response = restTemplate.exchange(
				endpoint + locationKey + "?apikey=" + apiKey + "&details=true",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<ForecastDto>(){});
			ForecastDto forecast = response.getBody();
			if(forecast == null) {
				throw new RuntimeException("No forecast for locationkey=" + locationKey );
			}
			return forecast; 
		}
		@GetMapping("/forecast-by-text")
		public ForecastDto getForecastByText(@RequestParam("text") String text){
			String locationKey = locationApi.getLocationKey(text);
			return getForecastByLocationKey(locationKey);
		}
}

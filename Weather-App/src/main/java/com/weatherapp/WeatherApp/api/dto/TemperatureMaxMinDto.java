package com.weatherapp.WeatherApp.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TemperatureMaxMinDto {
	@JsonProperty("Minimum")
	public TemperatureData minimum;
	
	@JsonProperty("Maximum")
	public TemperatureData maximum;
}

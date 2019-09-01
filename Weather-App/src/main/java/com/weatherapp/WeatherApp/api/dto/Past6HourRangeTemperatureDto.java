package com.weatherapp.WeatherApp.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Past6HourRangeTemperatureDto {
	@JsonProperty("Minimum")
	public TemperatureDto minimum;
	
	@JsonProperty("Maximum")
	public TemperatureDto maximum;
}

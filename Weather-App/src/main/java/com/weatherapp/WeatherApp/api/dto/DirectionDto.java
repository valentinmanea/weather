package com.weatherapp.WeatherApp.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DirectionDto {
	@JsonProperty("Degrees")
	public double degrees;
}

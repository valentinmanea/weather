package com.weatherapp.WeatherApp.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpeedDto {
	@JsonProperty("Value")
	public String value;

	@JsonProperty("Unit")
	public String unit;
}

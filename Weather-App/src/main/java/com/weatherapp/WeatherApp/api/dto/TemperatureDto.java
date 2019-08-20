package com.weatherapp.WeatherApp.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TemperatureDto {
	@JsonProperty("Metric")
	public TemperatureData metric;

	@JsonProperty("Imperial")
	public TemperatureData imperial;
}

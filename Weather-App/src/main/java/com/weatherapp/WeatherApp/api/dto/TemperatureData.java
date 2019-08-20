package com.weatherapp.WeatherApp.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TemperatureData {
	@JsonProperty("Value")
	public String value;
	
	@JsonProperty("Unit")
	public String unit;

	@JsonProperty("UnitType")
	public String unitType;
}

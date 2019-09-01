package com.weatherapp.WeatherApp.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TemperatureSummaryDto {
	@JsonProperty("Past6HourRange")
	public Past6HourRangeTemperatureDto rangeTemperature;
}

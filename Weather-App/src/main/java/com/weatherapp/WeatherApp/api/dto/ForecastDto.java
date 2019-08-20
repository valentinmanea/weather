package com.weatherapp.WeatherApp.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ForecastDto {
	
	@JsonProperty("Headline")
	public HeadlineDto headline;
	
	@JsonProperty("DailyForecasts")
	public List<DailyForecastsDto> dailyForecasts;
}

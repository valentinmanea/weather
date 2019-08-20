package com.weatherapp.WeatherApp.api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DailyForecastsDto {
	@JsonProperty("Date")
	public Date date;
	
	@JsonProperty("EpochDate")
    public Date epochDate;
	
	@JsonProperty("Temperature")
    public TemperatureMaxMinDto temperature;
       
	@JsonProperty("Day")
	public ForecastDetailsDto dayDetails;
	
	@JsonProperty("Night")
	public ForecastDetailsDto nightDetails;
}

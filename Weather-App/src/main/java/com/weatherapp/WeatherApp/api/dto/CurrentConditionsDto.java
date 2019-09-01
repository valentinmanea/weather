package com.weatherapp.WeatherApp.api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrentConditionsDto {
	@JsonProperty("LocalObservationDateTime")
	public Date localObservationDateTime;
	
	@JsonProperty("WeatherText")
	public String weatherText;
	
	@JsonProperty("WeatherIcon")
	public String weatherIcon;
	
	@JsonProperty("HasPrecipitation")
	public boolean hasPrecipitation;
	
	@JsonProperty("PrecipitationType")
	public boolean precipitationType;

	@JsonProperty("IsDayTime")
	public boolean isDayTime;
	
	@JsonProperty("Temperature")
	public TemperatureDto temperatureDto;
	
	@JsonProperty("TemperatureSummary")
	public TemperatureSummaryDto summary;
}

package com.weatherapp.WeatherApp.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WindDto {
	@JsonProperty("Speed")
	public SpeedDto speedDto;
	
	@JsonProperty("Direction")
	public DirectionDto directionDto;
}

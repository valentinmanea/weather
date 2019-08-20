package com.weatherapp.WeatherApp.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdministrativeAreaDto {
	@JsonProperty("ID")
	public String id;
	
	@JsonProperty("LocalizedName")
	public String localizedName;
}

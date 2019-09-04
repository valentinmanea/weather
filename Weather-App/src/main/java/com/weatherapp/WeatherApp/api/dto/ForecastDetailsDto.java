package com.weatherapp.WeatherApp.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ForecastDetailsDto {
	
	@JsonProperty("Icon")
	public String icon;
	
	@JsonProperty("IconPhrase")
	public String iconPhrase;
	
    @JsonProperty("HasPrecipitation")
    public boolean hasPrecipitation;
    
    @JsonProperty("Wind")
    public WindDto windDto;
}

package com.weatherapp.WeatherApp.api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HeadlineDto {
	
	@JsonProperty("EffectiveDate")
	public Date effectiveDate;
	
	@JsonProperty("EffectiveEpochDate")
	public Date effectiveEpochDate;

	@JsonProperty("Severity")
	public int Sseverity;
	
	@JsonProperty("Text")
	public String text;
	
	@JsonProperty("Category")
	public String category;
	
	@JsonProperty("EndDate")
	public Date endDate;
	
	@JsonProperty("EndEpochDate")
	public Date endEpochDate;
}

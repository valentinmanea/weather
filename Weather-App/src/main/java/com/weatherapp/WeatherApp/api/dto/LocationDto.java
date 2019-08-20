package com.weatherapp.WeatherApp.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationDto {
	
	@JsonProperty("Version")
	public String version;
	
	@JsonProperty("Key")
	public String key;
	
	@JsonProperty("Type")
	public String type;
	
	@JsonProperty("Rank")
	public String rank;
	
	@JsonProperty("LocalizedName")
	public String localizedName;
	
	@JsonProperty("Country")
	public CountryDto countryDto;
	
	@JsonProperty("AdministrativeArea")
	public AdministrativeAreaDto administrativeAreaDto;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getLocalizedName() {
		return localizedName;
	}

	public void setLocalizedName(String localizedName) {
		this.localizedName = localizedName;
	}

	public CountryDto getCountryDto() {
		return countryDto;
	}

	public void setCountryDto(CountryDto countryDto) {
		this.countryDto = countryDto;
	}

	public AdministrativeAreaDto getAdministrativeAreaDto() {
		return administrativeAreaDto;
	}

	public void setAdministrativeAreaDto(AdministrativeAreaDto administrativeAreaDto) {
		this.administrativeAreaDto = administrativeAreaDto;
	}
	
	
	
}

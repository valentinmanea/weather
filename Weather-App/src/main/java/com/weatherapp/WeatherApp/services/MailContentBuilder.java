package com.weatherapp.WeatherApp.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.weatherapp.WeatherApp.api.dto.CurrentConditionsDto;
import com.weatherapp.WeatherApp.api.dto.ForecastDto;
import com.weatherapp.WeatherApp.api.location.ForecastAPI;
import com.weatherapp.WeatherApp.api.location.LocationCurrentConditionsAPI;
import com.weatherapp.WeatherApp.entities.Subscription;

@Service
public class MailContentBuilder {
 
    private TemplateEngine templateEngine;
    
    @Autowired
    LocationCurrentConditionsAPI currentConditions;
    
    @Autowired
    ForecastAPI forecastApi;
    
    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
 
    public String createContentForWeatherMail(Subscription subscription) {
        Context context = new Context();
        fillMailWithGeneralInformation(subscription, context);
        
        fillMailWithCurrentConditions(subscription, context);
        
        fillMailWithForecast(subscription, context);
        
        return templateEngine.process("weather-information", context);
    }

	private void fillMailWithGeneralInformation(Subscription subscription, Context context) {
		context.setVariable("username", subscription.getUser().getUsername());
        context.setVariable("locationName", subscription.getLocationName());
        context.setVariable("currentHour", LocalDate.now());
	}

	private void fillMailWithForecast(Subscription subscription, Context context) {
		ForecastDto forecastForLocation = getForecastForLocation(subscription.getLocationName());
		context.setVariable("maximumTemperature", forecastForLocation.dailyForecasts.get(0).temperature.maximum.value + forecastForLocation.dailyForecasts.get(0).temperature.maximum.unit);
        context.setVariable("minimumTemperature", forecastForLocation.dailyForecasts.get(0).temperature.minimum.value + forecastForLocation.dailyForecasts.get(0).temperature.minimum.unit);
        context.setVariable("effectiveDate", forecastForLocation.headline.effectiveDate);
        context.setVariable("endDate", forecastForLocation.headline.endDate);
        context.setVariable("forecastTemperatureMinim", forecastForLocation.dailyForecasts.get(0).temperature.minimum.value + forecastForLocation.dailyForecasts.get(0).temperature.minimum.unit);
        context.setVariable("forecastTemperatureMaxim", forecastForLocation.dailyForecasts.get(0).temperature.maximum.value + forecastForLocation.dailyForecasts.get(0).temperature.maximum.unit);
        context.setVariable("nightPrecipitation",forecastForLocation.dailyForecasts.get(0).nightDetails.hasPrecipitation ? "Da" : "Nu");
        context.setVariable("dayPrecipitation",forecastForLocation.dailyForecasts.get(0).dayDetails.hasPrecipitation ? "Da" : "Nu");
	}

	private void fillMailWithCurrentConditions(Subscription subscription, Context context) {
		CurrentConditionsDto currentConditionsForLocation = getCurrentConditionsForLocation(subscription.getLocationName());
		context.setVariable("currentTemperature", currentConditionsForLocation.temperatureDto.metric.value + currentConditionsForLocation.temperatureDto.metric.unit);
        context.setVariable("currentWeather", currentConditionsForLocation.weatherText);
        context.setVariable("precipitation", currentConditionsForLocation.hasPrecipitation ? "Da" : "Nu");
	}
    
    private CurrentConditionsDto getCurrentConditionsForLocation(String location){
    	return currentConditions.getCurrentConditionForCityName(location);
    }
    
    private ForecastDto getForecastForLocation(String location){
    	return forecastApi.getForecastByText(location);
    }
}
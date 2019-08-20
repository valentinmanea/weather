package com.weatherapp.WeatherApp.errors;
/** 
 * @author kamal berriga
 *
 */
public class WeatherError {

    private String errorMessage;

    public WeatherError(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}

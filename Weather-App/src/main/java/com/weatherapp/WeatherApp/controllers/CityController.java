package com.weatherapp.WeatherApp.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weatherapp.WeatherApp.entities.FavouriteCity;
import com.weatherapp.WeatherApp.entities.Subscription;
import com.weatherapp.WeatherApp.entities.User;
import com.weatherapp.WeatherApp.errors.WeatherError;
import com.weatherapp.WeatherApp.repo.FavouriteCityRepo;
import com.weatherapp.WeatherApp.repo.UserRepo;

@RestController
@RequestMapping("/city")
@CrossOrigin(origins = "http://localhost:4200")
public class CityController {
	
	@Autowired 
	UserRepo userRepo;
	
	@Autowired 
	FavouriteCityRepo favouriteCityRepo;
	
	@PostMapping("/favourites/add")
	public ResponseEntity<FavouriteCity> addCityToFavorites(@RequestBody String name){
		User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		
		FavouriteCity favouriteCity = new FavouriteCity(name);
		favouriteCity.setUser(user);
		user.addFavouriteCity(favouriteCity);
		
		if(favouriteCityRepo.findByCityNameForCurrentUser(name, user.getId()) != null) {
		return new ResponseEntity(new WeatherError("City " + name + " already exists"), HttpStatus.CONFLICT);
	}
		return new ResponseEntity<FavouriteCity>(favouriteCityRepo.save(favouriteCity), HttpStatus.CREATED); 
	}
	
	@DeleteMapping("/favourites/delete")
	public void deleteCityFromFavorites(@RequestParam("name") String name){
		System.out.println("asdadsa");
		favouriteCityRepo.deleteByCityName(name);
	}
	
	@GetMapping("/favourites/all")
	public List<String> getAllFavoritesForUser(){
		User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		return favouriteCityRepo.findByUserId(user.getId()).stream().map(FavouriteCity::getCityName).collect(Collectors.toList());
	}
}

package com.weatherapp.WeatherApp.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weatherapp.WeatherApp.entities.Subscription;
import com.weatherapp.WeatherApp.entities.User;
import com.weatherapp.WeatherApp.errors.WeatherError;
import com.weatherapp.WeatherApp.repo.SubscriptionRepo;
import com.weatherapp.WeatherApp.repo.UserRepo;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
	@Autowired
	SubscriptionRepo subscriptionRepo;
	
	@Autowired
	UserRepo userRepo; 
	
	@PostMapping("/add")
	public ResponseEntity<Subscription> addSubscription(@RequestBody Subscription subscription) {
		User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		subscription.setUser(user);
		subscription.setActive(true);
		if(subscriptionRepo.getSubscriptionForCurrentUser(subscription.getLocationName(), user.getId()) != null) {
			return new ResponseEntity(new WeatherError("Subscription with locationName " + subscription.getLocationName() + " already exists"), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Subscription>(subscriptionRepo.save(subscription), HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public List<Subscription> getAllSubscriptions(){
		return subscriptionRepo.findAll();
	}
	
	
	@DeleteMapping("/delete")
	public void deleteSubscription(@PathVariable("id") Long id) {
		subscriptionRepo.deleteById(id);
	}
	
	@GetMapping("/current-user/all")
	public List<Subscription> getAllSubscriptionsForCurrentUser(){
		User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<Subscription> subscriptions = subscriptionRepo.findByUserId(user.getId());
		return subscriptions; 
	}
	
	@PostMapping("/current-user/status")
	public boolean changeStatus(@RequestBody Subscription subscription){
		User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println("Am salvat" + subscription);
		Subscription subscriptionDB = subscriptionRepo.findByLocationNameForCurrentUser(subscription.getLocationName(), user.getId());
		subscriptionDB.setActive(!subscriptionDB.isActive());
		System.out.println("Am salvat" + subscriptionDB.isActive());
		subscriptionRepo.save(subscriptionDB);
		return subscriptionDB.isActive();
	
	}
	
}

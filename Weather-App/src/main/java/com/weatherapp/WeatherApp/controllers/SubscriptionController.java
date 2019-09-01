package com.weatherapp.WeatherApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Subscription addSubscription(@RequestBody Subscription subscription) {
		subscription.user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		subscription.isActive = true;
		return subscriptionRepo.save(subscription);
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
		List<Subscription> list = subscriptionRepo.findByUserId(user.getId());
		list.stream().forEach(s->System.out.println(s.locationName));
		return subscriptionRepo.findByUserId(user.getId());
	}
	
}

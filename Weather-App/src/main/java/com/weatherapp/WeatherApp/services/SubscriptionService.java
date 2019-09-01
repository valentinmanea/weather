package com.weatherapp.WeatherApp.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weatherapp.WeatherApp.entities.Subscription;
import com.weatherapp.WeatherApp.repo.SubscriptionRepo;

@Service
public class SubscriptionService {
	
	@Autowired
	SubscriptionRepo subscriptionRepo;
	
	public void checkSubscriptionActive() {
		List<Subscription> subscriptions = subscriptionRepo.findActiveSubscription();
		for(Subscription subscription: subscriptions) {
			if(LocalDate.now().isAfter(LocalDate.from(subscription.creationDate.toInstant()
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate()).plusDays(subscription.duration))) {
				subscription.isActive = false;
				subscriptionRepo.save(subscription);
				System.out.println("am dezactivat subscriptia");
			}
		}
		
	}

}

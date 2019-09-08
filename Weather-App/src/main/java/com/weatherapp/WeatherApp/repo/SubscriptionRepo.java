package com.weatherapp.WeatherApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.weatherapp.WeatherApp.entities.Subscription;

public interface SubscriptionRepo extends JpaRepository<Subscription, Long> { 

	@Query(value="SELECT s FROM Subscription s WHERE s.user.id=:id")
	List<Subscription> findByUserId(@Param("id") Long id);
	
	@Query(value="SELECT s FROM Subscription s WHERE s.isActive=true")
	List<Subscription> findActiveSubscription();

	Subscription findByLocationName(String locationName);
	
	
	@Query(value="SELECT s FROM Subscription s WHERE s.user.id=:id AND s.locationName=:locationName")
	Subscription findByLocationNameForCurrentUser(String locationName, Long id);


}

package com.weatherapp.WeatherApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weatherapp.WeatherApp.entities.User;

public interface UserRepo extends JpaRepository<User,Long> {
	 public User findByUsername(String username);
	 
	 public User findByEmail(String email);
}

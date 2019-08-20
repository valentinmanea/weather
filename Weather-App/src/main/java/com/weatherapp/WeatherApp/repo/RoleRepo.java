package com.weatherapp.WeatherApp.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weatherapp.WeatherApp.entities.Role;

public interface RoleRepo extends JpaRepository<Role,Long>{
	
	public Optional<Role> findFirstByName(String string);

}

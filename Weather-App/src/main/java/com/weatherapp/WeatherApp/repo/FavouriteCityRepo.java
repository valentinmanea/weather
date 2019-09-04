package com.weatherapp.WeatherApp.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.weatherapp.WeatherApp.entities.FavouriteCity;

@Repository
public interface FavouriteCityRepo extends JpaRepository<FavouriteCity, Long>{

	
	@Query(value="SELECT f FROM FavouriteCity f WHERE f.user.id=:id")
	List<FavouriteCity> findByUserId(@Param("id") Long id);
	@Transactional
	  @Modifying
	@Query(value="DELETE FROM FavouriteCity f WHERE f.cityName=:cityName")
	void deleteByCityName(String cityName);
}

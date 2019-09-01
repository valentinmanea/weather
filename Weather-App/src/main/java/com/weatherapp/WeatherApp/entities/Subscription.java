package com.weatherapp.WeatherApp.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="SUBSCRIPTION")
public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;

	@Column(name= "location_name")
	public String locationName;
	
	@Column(name= "duration")
	public int duration;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	public User user;
	
	@CreationTimestamp
	public Date creationDate;
	
	@Column(name="is_active")
	public boolean isActive;

	@Override
	public String toString() {
		return "Subscription [id=" + id + ", locationName=" + locationName +", duration=" + duration
				+ ", user=" + user + "]";
	}
	
	
}

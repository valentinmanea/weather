package com.weatherapp.WeatherApp.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User  implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@Column(name = "username")
	private String username; 
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "enabled")
    private boolean enabled;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Role role;
	
	@Column(name="country")
	private String country;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private List<Subscription> subscriptions; 
	
	@OneToMany(mappedBy="user")
	private List<FavouriteCity> favouriteCities;
	
	public void addSubscription(Subscription subscription) {
		if(subscriptions == null) {
			subscriptions = Arrays.asList(subscription);
		}else {
			subscriptions.add(subscription);
		}
	}
	
	public void addFavouriteCity(FavouriteCity favouriteCity) {
		if(favouriteCities == null) {
			favouriteCities = Arrays.asList(favouriteCity);
		}else {
			favouriteCities.add(favouriteCity);
		}
	}
	@JsonIgnore
	public List<Subscription> getSubscriptions(){
		return subscriptions;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public boolean isDisabled() {
		return !enabled;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.getName()));
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}

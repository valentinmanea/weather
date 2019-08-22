package com.weatherapp.WeatherApp.controllers;

import java.security.Principal;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.weatherapp.WeatherApp.dtos.UserDto;
import com.weatherapp.WeatherApp.entities.User;
import com.weatherapp.WeatherApp.errors.WeatherError;
import com.weatherapp.WeatherApp.services.UserService;
/** 
 * @author kamal berriga
 *
 */
@RestController
@RequestMapping("auth")
public class AuthController {

	public static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private UserService userService;
	
  

	// request method to create a new account by a guest
	@CrossOrigin
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody UserDto newUserDto, WebRequest request) {
		System.out.println("request: "+request+"request locale"+request.getLocale()+"request context path" + request.getContextPath());
		if (userService.findByUsername(newUserDto.username) != null) {
			return new ResponseEntity(new WeatherError("user with username " + newUserDto.username + "already exists"), HttpStatus.CONFLICT);
		}
		User user = userService.save(newUserDto);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping("/login")
	public Principal user(Principal principal) { 
		logger.info("user logged "+principal);
		
		return principal;
	}
	
	@PostConstruct()
	public void init() {
		UserDto user = new UserDto();
		user.email = "nicolae_stefan@yahoo.com";
		user.firtName = "Nicolae";
		user.lastName = "Stefan";
		user.username = "Stefan";
		user.password = "password";
		this.userService.save(user);
	}

}

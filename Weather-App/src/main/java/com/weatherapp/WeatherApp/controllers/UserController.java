package com.weatherapp.WeatherApp.controllers;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.weatherapp.WeatherApp.dtos.UserDto;
import com.weatherapp.WeatherApp.repo.UserRepo;
import com.weatherapp.WeatherApp.services.UserService;

@RestController
@RequestMapping("/")
@CrossOrigin
public class UserController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
    private UserService userService;


	@RequestMapping(method = RequestMethod.GET, path = "/users")
	public List<UserDto> findAllUsers() {
		List<UserDto> usersDto = userRepo.findAll().stream().map(user -> modelMapper.map(user, UserDto.class))
				.collect(toList());
		usersDto.forEach(System.out::println);
		return usersDto;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/user")
	public void addUser(@RequestBody UserDto userDto) {
		userService.save(userDto);
	}

}

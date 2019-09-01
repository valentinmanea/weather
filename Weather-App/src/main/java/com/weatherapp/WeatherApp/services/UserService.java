package com.weatherapp.WeatherApp.services;


import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.weatherapp.WeatherApp.dtos.UserDto;
import com.weatherapp.WeatherApp.entities.Role;
import com.weatherapp.WeatherApp.entities.User;
import com.weatherapp.WeatherApp.repo.RoleRepo;
import com.weatherapp.WeatherApp.repo.UserRepo;


@Service
public class UserService{
    @Autowired
	private UserRepo userRepo;
    
    @Autowired
    private RoleRepo roleRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private ModelMapper mapper;
    
    public User save(UserDto newUserDto) {
    	User user = new User();
    	Role role;
    	mapper.map(newUserDto, user);
    	Optional<Role> roleOptional = roleRepo.findFirstByName("USER");
    	if(!roleOptional.isPresent()) {
    		role = new Role("USER"); 
    		roleRepo.save(role);
    	}else {
    		role = roleOptional.get();
    	}
		user.setRole(role);
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	user.setEnabled(true);
    	user.setCountry(newUserDto.country);
		return userRepo.save(user);
	}

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    private boolean emailExist(String email) {
        User user = userRepo.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }
     
     
    public void saveRegisteredUser(User user) {
        userRepo.save(user);
    }
     
}
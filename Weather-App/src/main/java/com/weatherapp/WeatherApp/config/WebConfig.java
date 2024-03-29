package com.weatherapp.WeatherApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.weatherapp.WeatherApp.services.AppUserDetailsService;

@Configurable
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AppUserDetailsService appUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(appUserDetailsService);
		auth.authenticationProvider(authProvider());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(appUserDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200");
			}
		};
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Autowired
	LogoutSuccessHandler logoutSuccess;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().authorizeRequests()
				.antMatchers("/auth/login", "/country/all", "/auth/register", "/v2/api-docs", "/configuration/ui",
						"/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**")
				.permitAll().anyRequest().fullyAuthenticated().and().logout().logoutSuccessHandler(logoutSuccess)
				.permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST")).and().httpBasic().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and().csrf().disable();

	}

}

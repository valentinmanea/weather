package com.weatherapp.WeatherApp.tasks;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.weatherapp.WeatherApp.services.MailService;
import com.weatherapp.WeatherApp.services.SubscriptionService;

@Component
@EnableAsync
public class SendMailTask {
	
	@Autowired
	MailService mailService;
	
	@Autowired
	SubscriptionService subscriptionService;
	@Scheduled(cron="0 0/1 * * * *")
	@Async
	public void sendMail() {
		System.out.println("A fost ora" + LocalDate.now());
		subscriptionService.checkSubscriptionActive();
		mailService.sendMailsForAllSubscriptions();
	}
}

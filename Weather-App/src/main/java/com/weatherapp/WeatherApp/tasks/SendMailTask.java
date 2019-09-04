package com.weatherapp.WeatherApp.tasks;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.weatherapp.WeatherApp.services.MailService;

@Component
@EnableAsync
public class SendMailTask {
	
	@Autowired
	MailService mailService;
	
//	@Scheduled(cron="0 0/1 * * * *") //la fiecare minut
	@Scheduled(cron="0 0 8  * * *") // in fiecare zi la ora 8
	@Async
	public void sendMail() {
		System.out.println("A fost ora" + LocalDate.now());
		mailService.sendMailsForAllSubscriptions();
	}
}

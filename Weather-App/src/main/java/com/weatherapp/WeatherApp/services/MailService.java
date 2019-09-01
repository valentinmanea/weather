package com.weatherapp.WeatherApp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weatherapp.WeatherApp.entities.Subscription;
import com.weatherapp.WeatherApp.repo.SubscriptionRepo;

@Service
@RestController
public class MailService{
	
	Logger log = LoggerFactory.getLogger(MailService.class);
	
	public JavaMailSender emailSender;
	
	@Autowired
	public SubscriptionRepo subscriptionRepo;
	
	@Autowired
	public MailService(JavaMailSender emailSender) {
		  this.emailSender = emailSender;
	}
	
	@Autowired
	private MailContentBuilder  builder;
	
	public void sendMessage(Subscription subscription) {
			System.out.println("Subscription: " + subscription);
			emailSender.send(prepareMail(subscription));
	}
	public MimeMessagePreparator prepareMail(Subscription subscription) {
		 return mimeMessage -> {
		        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
		        messageHelper.setTo(subscription.user.getEmail());
		        messageHelper.setSubject("Forecast subscription");
		        String content = builder.createContentForWeatherMail(subscription);
		        messageHelper.setText(content,true);
		    };
	}
	@GetMapping("test")
	public void sendMailsForAllSubscriptions() {
		subscriptionRepo.findActiveSubscription().forEach(System.out::println);
		subscriptionRepo.findActiveSubscription().forEach(this::sendMessage);
	}
}

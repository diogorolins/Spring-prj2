package com.diogorolins.springprj1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.diogorolins.springprj1.services.EmailService;
import com.diogorolins.springprj1.services.MockEmailService;

@Configuration
@Profile("dev")
public class ConfigEmailDev {

	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}

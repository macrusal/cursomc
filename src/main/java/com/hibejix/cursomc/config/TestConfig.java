/**
 * 
 */
package com.hibejix.cursomc.config;

import java.text.ParseException;

import com.hibejix.cursomc.services.EmailService;
import com.hibejix.cursomc.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.hibejix.cursomc.services.DBService;

/**
 * @author msalvador
 *
 */
@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	DBService dbService; 
	
	@Bean
	public boolean instantiateDataase() throws ParseException {
		dbService.instantiateTestDataBase();
		return true;
	}

	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

}

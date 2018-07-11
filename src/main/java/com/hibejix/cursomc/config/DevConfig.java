/**
 * 
 */
package com.hibejix.cursomc.config;

import java.text.ParseException;

import com.hibejix.cursomc.services.EmailService;
import com.hibejix.cursomc.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.hibejix.cursomc.services.DBService;

/**
 * @author msalvador
 *
 */
@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	DBService dbService; 
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDataase() throws ParseException {
		
		if(!"create".equals(strategy)) {
			return false;
		}
		dbService.instantiateTestDataBase();
		return true;
	}

	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}

}

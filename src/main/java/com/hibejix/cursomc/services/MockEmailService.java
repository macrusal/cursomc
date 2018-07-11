package com.hibejix.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendMail(SimpleMailMessage simpleMailMessage) {

        LOG.info("Simulando o envio de email...");
        LOG.info(simpleMailMessage.toString());
        LOG.info("Email enviado!");
    }
}

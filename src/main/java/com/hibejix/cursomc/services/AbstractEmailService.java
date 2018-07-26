package com.hibejix.cursomc.services;

import com.hibejix.cursomc.domain.Cliente;
import com.hibejix.cursomc.domain.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {

        SimpleMailMessage simpleMailMessage = preparedSimpleMailMessageFromPedido(pedido);
        sendMail(simpleMailMessage);
    }

    protected SimpleMailMessage preparedSimpleMailMessageFromPedido(Pedido pedido) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(pedido.getCliente().getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("Pedido confirmado! Codigo..: " + pedido.getId());
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText(pedido.toString());
        return simpleMailMessage;
    }

    @Override
    public void sendNewPawwsordEmail(Cliente cliente, String newPassword) {

        SimpleMailMessage simpleMailMessage = preparedNewPasswordEmail(cliente, newPassword);
        sendMail(simpleMailMessage);
    }

    protected SimpleMailMessage preparedNewPasswordEmail(Cliente cliente, String newPassword) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(cliente.getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("Solicitação de nova senha..: ");
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText("Nova senha...: " + newPassword);
        return simpleMailMessage;
    }
}

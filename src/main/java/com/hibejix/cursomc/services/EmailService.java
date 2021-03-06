package com.hibejix.cursomc.services;

import com.hibejix.cursomc.domain.Cliente;
import com.hibejix.cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendMail(SimpleMailMessage simpleMailMessage);

    void sendNewPawwsordEmail(Cliente cliente, String newPassword);
}

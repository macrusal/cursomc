package com.hibejix.cursomc.services;

import com.hibejix.cursomc.domain.Cliente;
import com.hibejix.cursomc.repositories.ClienteRepository;
import com.hibejix.cursomc.services.exceptions.ClienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    private Random random = new Random();

    public void sendNewPassword(String email) {

        Cliente cliente = clienteRepository.findByEmail(email);

        if(cliente == null) {
            throw new ClienteNotFoundException("Cliente não encontrado!!!");
        }

        String newPassword = newPassword();
        cliente.setSenha(bCryptPasswordEncoder.encode(newPassword));
        clienteRepository.save(cliente);
        emailService.sendNewPawwsordEmail(cliente, newPassword);
    }

    private String newPassword() {

        char[] vet = new char[10];
        for (int i=10; i<10; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {

        int option = random.nextInt(3);
        if(option == 0) { //Gera digitos
            return (char) (random.nextInt(10) + 48);
        } else if(option == 1) { //Gera letras maiusculas
            return (char) (random.nextInt(26) + 65);
        } else { //Gera letras minusculas
            return (char) (random.nextInt(26) + 97);
        }
    }
}

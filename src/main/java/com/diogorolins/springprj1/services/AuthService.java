package com.diogorolins.springprj1.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.diogorolins.springprj1.domain.Client;
import com.diogorolins.springprj1.exceptions.ObjectNotFoundException;
import com.diogorolins.springprj1.repositories.ClientRepository;

@Service
public class AuthService {

	@Autowired
	private ClientRepository repository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();

	public void sendNewPassword(String email) {
		Client client = repository.findByEmail(email);
		if (client == null) {
			throw new ObjectNotFoundException("Email não encontrado.");
		}

		String newPass = generateNewPassword();
		client.setPassword(encoder.encode(newPass));
		repository.save(client);
		emailService.sendNewPasswordEmail(client, newPass);
	}

	private String generateNewPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		} else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}

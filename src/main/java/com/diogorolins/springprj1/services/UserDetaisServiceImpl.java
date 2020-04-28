package com.diogorolins.springprj1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.diogorolins.springprj1.domain.Client;
import com.diogorolins.springprj1.repositories.ClientRepository;
import com.diogorolins.springprj1.security.UserSS;

@Service
public class UserDetaisServiceImpl implements UserDetailsService{

	@Autowired
	private ClientRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Client cli = repository.findByEmail(email);
		if(cli == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(cli.getId(), cli.getEmail(), cli.getPassword(), cli.getRoles());
	}

}

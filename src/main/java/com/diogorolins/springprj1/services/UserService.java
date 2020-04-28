package com.diogorolins.springprj1.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.diogorolins.springprj1.security.UserSS;

public class UserService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

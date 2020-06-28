package br.com.clover.service;

import br.com.clover.model.User;

public interface UserService {

	User save(User user);

	User findByUsername(String username);
	
	User findByUsernameAndPassword(String username, String password);
	
	Boolean exists(String username);
}

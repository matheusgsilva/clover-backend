package br.com.clover.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.clover.model.User;
import br.com.clover.repository.UserRepository;

@Service
public class UserServiceBean implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User findByUsernameAndPassword(String username, String password) {

		User user = findByUsername(username);
		if (user != null) {
			if (BCrypt.checkpw(password, user.getPassword())) {
				return user;
			}
			return null;
		}
		return null;
	}
	
	public Boolean exists(String username) {
		return userRepository.isExists(username);
	}
}

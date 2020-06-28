package br.com.clover.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clover.model.Jwt;
import br.com.clover.model.User;
import br.com.clover.repository.JwtRepository;

@Service
public class JwtServiceBean implements JwtService {

	@Autowired
	private JwtRepository jwtRepository;

	@Transactional
	public Jwt save(Jwt jwt) {
		return jwtRepository.save(jwt);
	}

	public Jwt findByUser(User user) {
		return jwtRepository.findByUser(user);
	}

	public Jwt findByJwt(String jwt) {
		return jwtRepository.findByJwt(jwt);
	}

	public Jwt getLastJwt(User user) {
		return jwtRepository.getLastJwt(user);
	}

}

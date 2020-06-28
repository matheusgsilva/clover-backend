package br.com.clover.service;

import br.com.clover.model.Jwt;
import br.com.clover.model.User;

public interface JwtService {

	Jwt save(Jwt jwt);

	Jwt findByUser(User user);
	
	Jwt findByJwt(String jwt);
	
	Jwt getLastJwt(User user);
}

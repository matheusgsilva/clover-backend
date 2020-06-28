package br.com.clover.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.clover.model.Jwt;
import br.com.clover.model.User;

@Repository
public interface JwtRepository extends JpaRepository<Jwt, Integer> {
	
	Jwt findByUser(User user);
	
	Jwt findByJwt(String jwt);
	
	@Query("SELECT j FROM Jwt j WHERE j.id = (SELECT MAX(j.id) FROM Jwt j WHERE j.user = :user AND DATE(j.createDate) = DATE(CURRENT_DATE))")
	Jwt getLastJwt(@Param("user") User user);
}
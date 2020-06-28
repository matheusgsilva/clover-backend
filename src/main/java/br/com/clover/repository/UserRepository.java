package br.com.clover.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.clover.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);
	
	@Query("SELECT CASE WHEN (COUNT(u) > 0) THEN true ELSE false END FROM User u WHERE u.username = :username AND u.active = 1")
	Boolean isExists(@Param("username") String username);
}
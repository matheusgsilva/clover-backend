package br.com.clover;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.clover.model.Jwt;
import br.com.clover.model.User;
import br.com.clover.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

	@Autowired
	private JwtService jwtService;

	@Value("${jwt.secret}")
	private String secret;

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token, User user) {
		final Date expiration = getExpirationDateFromToken(token);
		if (expiration.before(new Date()) || !jwtService.getLastJwt(user).getJwt().equals(token))
			return true;
		else
			return false;
	}

	public Jwt generateToken(User user) {
		Map<String, Object> claims = new HashMap<>();
		return jwtService.save(new Jwt(doGenerateToken(claims, user.getUsername()), new Date(), user));
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 60 * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Boolean validateToken(String token) {
		try {
			User user = jwtService.findByJwt(token).getUser();
			if (user.getUsername().equals(getUsernameFromToken(token)) && !isTokenExpired(token, user))
				return true;

			return false;
		} catch (Exception e) {
			return false;
		}
	}
}
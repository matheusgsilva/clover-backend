package br.com.clover.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.clover.JwtTokenUtil;
import br.com.clover.enums.ACTIVE;
import br.com.clover.model.Jwt;
import br.com.clover.model.User;
import br.com.clover.request.LoginRequest;
import br.com.clover.response.LoginResponse;
import br.com.clover.response.ResponseAPI;
import br.com.clover.service.UserService;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<ResponseAPI> login(@RequestBody LoginRequest loginRequest) {

		try {
			User user = userService.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());

			if (user != null && user.getActive().equals(ACTIVE.YES)) {
				Jwt jwt = jwtTokenUtil.generateToken(user);

				return new ResponseEntity<ResponseAPI>(
						new ResponseAPI(200, "OK",
								new LoginResponse(user.getUsername(), user.getEmail(), user.getName(), jwt.getJwt())),
						HttpStatus.OK);

			} else {

				return new ResponseEntity<ResponseAPI>(new ResponseAPI(200, "NOT_FOUND", null),
						HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception ex) {
			ex.printStackTrace();

			return new ResponseEntity<ResponseAPI>(HttpStatus.UNAUTHORIZED);
		}
	}
}
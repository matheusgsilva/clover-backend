package br.com.clover.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.clover.enums.ACTIVE;
import br.com.clover.enums.ADMIN;
import br.com.clover.model.User;
import br.com.clover.request.UserRequest;
import br.com.clover.response.ResponseAPI;
import br.com.clover.response.UserResponse;
import br.com.clover.service.JwtService;
import br.com.clover.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/add", method = RequestMethod.POST)
	public ResponseEntity<ResponseAPI> add(@RequestBody UserRequest userRequest,
			@RequestHeader(value = "Authorization") String authorization) {

		try {
			User user = jwtService.findByJwt(authorization).getUser();

			if (user != null) {
				if (user.getActive().equals(ACTIVE.YES) && user.getAdmin().equals(ADMIN.YES)) {
					if (!userService.exists(userRequest.getUsername())) {
						User newUser = userService.save(new User(userRequest.getUsername(), userRequest.getName(),
								userRequest.getEmail(), new Date(), userRequest.getPassword(),
								ACTIVE.YES, userRequest.getAdmin()));

						return new ResponseEntity<ResponseAPI>(
								new ResponseAPI(200, "OK", new UserResponse(newUser.getUsername(), newUser.getName(),
										newUser.getEmail(), newUser.getCreateDate())),
								HttpStatus.OK);
					} else
						return new ResponseEntity<ResponseAPI>(new ResponseAPI(304, "ALREADY_EXISTS", null),
								HttpStatus.OK);
				}
				return new ResponseEntity<ResponseAPI>(new ResponseAPI(200, "NOT_ADDED", null), HttpStatus.OK);

			} else
				return new ResponseEntity<ResponseAPI>(new ResponseAPI(200, "NOT_FOUND", null), HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<ResponseAPI>(new ResponseAPI(400, "BAD_REQUEST", null), HttpStatus.BAD_REQUEST);
		}
	}

}
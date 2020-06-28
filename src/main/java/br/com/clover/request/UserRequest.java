package br.com.clover.request;

import br.com.clover.enums.ADMIN;

public class UserRequest {

	private String username;
	private String name;
	private String email;
	private ADMIN admin;
	private String password;

	public UserRequest(String username, String name, String email, ADMIN admin, String password) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.admin = admin;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ADMIN getAdmin() {
		return admin;
	}

	public void setAdmin(ADMIN admin) {
		this.admin = admin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

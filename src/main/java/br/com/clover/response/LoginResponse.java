package br.com.clover.response;

public class LoginResponse {

	private String username;
	private String email;
	private String name;
	private String jwt;

	public LoginResponse(String username, String email, String name, String jwt) {
		super();
		this.username = username;
		this.email = email;
		this.name = name;
		this.jwt = jwt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

}

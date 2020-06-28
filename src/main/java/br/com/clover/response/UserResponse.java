package br.com.clover.response;

import java.util.Date;

public class UserResponse {

	private String username;
	private String name;
	private String email;
	private Date createDate;

	public UserResponse(String username, String name, String email, Date createDate) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.createDate = createDate;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}

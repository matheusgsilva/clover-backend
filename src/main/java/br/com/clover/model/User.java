package br.com.clover.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.clover.enums.ACTIVE;
import br.com.clover.enums.ADMIN;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String username;

	@Column
	private String name;

	@Column
	private String email;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	private Date createDate;

	@Column
	@JsonIgnore
	private String password;

	@Column
	private ACTIVE active;

	@Column
	private ADMIN admin;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Jwt> jwts;

	public User() {
	}

	public User(String username, String name, String email, Date createDate, String password, ACTIVE active,
			ADMIN admin) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.createDate = createDate;
		this.password = password;
		this.active = active;
		this.admin = admin;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ACTIVE getActive() {
		return active;
	}

	public void setActive(ACTIVE active) {
		this.active = active;
	}

	public ADMIN getAdmin() {
		return admin;
	}

	public void setAdmin(ADMIN admin) {
		this.admin = admin;
	}

	public List<Jwt> getJwts() {
		return jwts;
	}

	public void setJwts(List<Jwt> jwts) {
		this.jwts = jwts;
	}

}
package com.delectable.userauth.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username")
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(UserViews.Simple.class)
	private Long id;

	@NotBlank
	@Size(max = 20)
	@JsonView(UserViews.Simple.class)
	private String username;

	@Size(max = 50)
	@Email
	@JsonView(UserViews.Simple.class)
	private String email;

	@Size(max = 120)
	@JsonView(UserViews.Detailed.class)
	private String password;

	@Enumerated(EnumType.STRING)
	@JsonView(UserViews.Simple.class)
	private ERole role;

	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ERole getRole() {
		return role;
	}

	public void setRole(ERole role) {
		this.role = role;
	}

	
}



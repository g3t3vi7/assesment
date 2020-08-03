package com.test.persondemo.models;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "users")
@Table(name = "users")
public class User {

	@Id
	private UUID id;

	@Column(name = "username", unique = true)
	private String username;
	private String password;
	private Boolean enabled = true;

	@OneToMany(cascade = ALL, fetch = EAGER)
	List<UserAuthority> userAuthorities = new ArrayList<>();

	User() {
	}

	public User(UUID id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public User(User user) {
		this.id = user.id;
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.enabled = user.getEnabled();
		this.userAuthorities = new ArrayList<>(user.getUserAuthorities());
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<UserAuthority> getUserAuthorities() {
		return userAuthorities;
	}

	public void setUserAuthorities(List<UserAuthority> userAuthorities) {
		this.userAuthorities = userAuthorities;
	}

	public void addAuthorities(String authority) {
		this.userAuthorities.add(new UserAuthority(this, authority));
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled + "]";
	}

}

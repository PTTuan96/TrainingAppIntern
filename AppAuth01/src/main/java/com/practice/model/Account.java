package com.practice.model;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.practice.security.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Account implements UserDetails {
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private int id;
	@JsonIgnore
	private Set<? extends GrantedAuthority> grantedAuthority;
	private String email;
	@JsonIgnore
	private String password;
	private String username;
	private Role role;
	private String token;

	@JsonIgnore
	private boolean isAccountNonExpired;

	@JsonIgnore
	private boolean isAccountNonLocked;

	@JsonIgnore
	private boolean isCredentialsNonExpired;

	@JsonIgnore
	private boolean isEnabled;
	@JsonIgnore
	private Profile profile;

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return grantedAuthority;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return isAccountNonExpired;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return isAccountNonLocked;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return isCredentialsNonExpired;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isEnabled;
	}

	public Account(String email, String password, String username, Role role, boolean isAccountNonExpired,
			boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
		this.email = email;
		this.password = password;
		this.username = username;
		this.role = role;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
	}

	public Account() {

	}

}

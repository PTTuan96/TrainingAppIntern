package com.practice.model;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account implements UserDetails {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Set<? extends GrantedAuthority> grantedAuthority;
	private int id;
	private String username;
	private String password;
	private String email;
	private String role;
	private Profile profile;
//	private boolean isAccountNonExpired;
//	private boolean isAccountNonLocked;
//	private boolean isCredentialsNonExpired;
//	private boolean isEnabled;
//	private Date create_at;
//	private Date update_at;

	public Set<? extends GrantedAuthority> getGrantedAuthority() {
		return grantedAuthority;
	}

	public void setGrantedAuthority(Set<? extends GrantedAuthority> grantedAuthority) {
		this.grantedAuthority = grantedAuthority;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthority;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

//	@Override
//	public String toString() {
//		return "Account [grantedAuthority=" + grantedAuthority + ", password=" + password + ", email=" + email
//				+ ", isAccountNonExpired=" + isAccountNonExpired + ", isAccountNonLocked=" + isAccountNonLocked
//				+ ", isCredentialsNonExpired=" + isCredentialsNonExpired + ", isEnabled=" + isEnabled + "]";
//	}

}

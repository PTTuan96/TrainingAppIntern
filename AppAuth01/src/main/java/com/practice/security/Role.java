package com.practice.security;

import com.google.common.collect.Sets;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import static com.practice.security.Permission.*;
public enum Role {
	USER(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(MEMBER_READ, MEMBER_WRITE)),
	MEMBER(Sets.newHashSet());
	
	private final Set<Permission> permissions;

	
	private Role(Set<Permission> permissions) {
		this.permissions = permissions;
	}


	public Set<Permission> getPermissions() {
		return permissions;
	}


	public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
					.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
					.collect(Collectors.toSet());
		
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permissions;
	}
}

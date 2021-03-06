package com.practice.security;

public enum Permission {
	TOURNAMENT_READ("tournament:read"),
	TOURNAMENT_WRITE("tournament:write"),
	TEAM_READ("team:read"),
	TEAM_WRITE("team:write"),
	MEMBER_READ("member:read"),
	MEMBER_WRITE("member:write");
	
	private final String permission;

	private Permission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
	
	
}

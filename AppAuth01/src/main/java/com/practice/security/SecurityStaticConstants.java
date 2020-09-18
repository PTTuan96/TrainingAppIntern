package com.practice.security;

public class SecurityStaticConstants {
    public static final String SIGN_UP_URLS = "/api/v1/auth/**";
    public static final String PROFILE_API = "/api/v1/profiles/**";
    public static final String TEAM_API = "/api/v1/teams/**";
    public static final String MEMBER_API = "/api/v1/members/**";
    public static final String HISTORY_API = "/api/v1/histories/**";
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 30_000; // 30 sec


}

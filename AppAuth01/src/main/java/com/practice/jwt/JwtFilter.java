package com.practice.jwt;

import static com.practice.security.SecurityStaticConstants.EXPIRATION_TIME;
import static com.practice.security.SecurityStaticConstants.SECRET;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.practice.inputform.LoginRequest;
import com.practice.model.Account;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static com.practice.security.SecurityStaticConstants.*;


public class JwtFilter extends UsernamePasswordAuthenticationFilter{

//	@Autowired
//	private ModelMapper modelMapper;
//	
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	
//	@Override
//	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//			throws AuthenticationException {
//		try {
//			LoginRequest authRequest = modelMapper.map(request.getInputStream(), LoginRequest.class); 
//			
//			Authentication authentication = new UsernamePasswordAuthenticationToken(
//					authRequest.getUsername(), 
//					authRequest.getPassword()
//			);
//			
//			Authentication authenticate = authenticationManager.authenticate(authentication);
//			
//			return authenticate;
//		}catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	@Override
//	protected void successfulAuthentication(HttpServletRequest request,
//											HttpServletResponse response,
//											FilterChain chain,
//											Authentication authResult) throws IOException, ServletException {
//        Date now = new Date(System.currentTimeMillis());
//        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
//	
//		String token = Jwts.builder()
//	                .setSubject(authResult.getName())
//	                .claim(AUTH_KEYWORD, authResult.getAuthorities())
//	                .setIssuedAt(now)
//	                .setExpiration(expiryDate)
//	                .signWith(SignatureAlgorithm.HS512, SECRET)
//	                .compact();
//		response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
//	}
	
	
}

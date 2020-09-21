package com.practice.jwt;

import static com.practice.security.SecurityStaticConstants.EXPIRATION_TIME;
import static com.practice.security.SecurityStaticConstants.SECRET;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.practice.inputform.LoginRequest;
import com.practice.model.Account;
import com.practice.service.AccountService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static com.practice.security.SecurityStaticConstants.*;


public class JwtFilter extends OncePerRequestFilter{
	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private AccountService accountService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {

			String jwt = getJWTFromRequest(request);

			if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {

				String email = tokenProvider.getUserEmailFromJWT(jwt);
				UserDetails userDetails = accountService.loadUserByUsername(email);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails        , userDetails.isCredentialsNonExpired(), userDetails.getAuthorities()
				); //current logged user   , status about userAccount             , role and authorities


				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}

		}catch (Exception ex) {
			logger.error("Could not set user authentication in security context", ex);
		}

		filterChain.doFilter(request, response);
	}

	public String getJWTFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(HEADER_STRING);

		if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith(TOKEN_PREFIX)) {
			return bearerToken.substring(7, bearerToken.length());
		}

		return null;
	}


}

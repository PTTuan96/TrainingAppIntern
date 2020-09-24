package com.practice.jwt;

import com.practice.dto.InfoUserDTO;
import com.practice.model.Account;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.practice.security.SecurityStaticConstants.EXPIRATION_TIME;
import static com.practice.security.SecurityStaticConstants.SECRET;

@Component
public class JwtTokenProvider {

    //Generate the token
    public String generateToken(Authentication authentication){
        Account account = (Account) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());

        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        String accountId = Long.toString(account.getId());

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", (Long.toString(account.getId())));
        claims.put("username", account.getUsername());
        claims.put("avatar", account.getProfile().getAvatar());
        claims.put("authorities", authentication.getAuthorities());
        claims.put("role", account.getRole());

        return Jwts.builder()
                .setSubject(accountId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    //Validate the token
    public boolean validateToken(String token) {
    	try {
    		Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
    		return true;
    	}catch (SignatureException ex) {
			System.out.print("Invalid JWT Signature");
		}catch (MalformedJwtException ex) {
			System.out.print("Invalid JWT Token");
		}catch (ExpiredJwtException ex) {
			System.out.print("Expired JWT token");
		}catch (UnsupportedJwtException e) {
			System.out.print("Unsupported JWT token");
		}catch (IllegalArgumentException e) {
			System.out.print("JWT claims string is empty");
		}
    	return false;
    }

    //Get user Id from token
    public String getUserEmailFromJWT(String token) {
    	// return all the key value inside the key (username, email, role)
    	Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    	String email = (String)claims.get("email");

    	return email;
    }

    public InfoUserDTO getUserDTOFromJWT(String token) {
    	// return all the key value inside the key (username, email, role)
    	Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    	InfoUserDTO info = new InfoUserDTO();
    	info.setRole((String)claims.get("role"));
    	info.setUsername((String)claims.get("username"));
    	info.setAvatar((String)claims.get("avatar"));
    	return info;
    }
}

package com.practice.jwt;

import com.practice.model.Account;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
        claims.put("email", account.getEmail());
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

    //Get user Id from token
}

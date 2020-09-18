package com.practice.controller;

import com.practice.jwt.JwtTokenProvider;
import com.practice.response.JWTLoginSuccessResponse;
//import com.practice.validator.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.practice.model.Account;
import com.practice.service.AccountService;

import static com.practice.security.SecurityStaticConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private AccountService accountService;

//	@Autowired
//	private AccountValidator accountValidator;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody Account account){ //BindingResult result
//		accountValidator.validate(account, result);

		Long newAccount = accountService.saveAccount(account);

		return new ResponseEntity<Account>(account, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody Account account) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						account.getEmail(),
						account.getPassword()
				)
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);

		return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
	}

	@GetMapping("/logout")
	public String getLogout() {
		return "Logout Success";
	}

	@GetMapping("/{email}")
	public Account getEmail(@PathVariable String email) {
		return accountService.getByUsername(email);
	}
}

package com.practice.controller;

import com.practice.dto.InfoUserDTO;
import com.practice.exception.EmailAlreadyExistsException;
import com.practice.inputform.LoginRequest;
import com.practice.jwt.JwtFilter;
import com.practice.jwt.JwtTokenProvider;
import com.practice.response.JWTLoginSuccessResponse;
import com.practice.response.ResponseQuery;

//import com.practice.validator.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.practice.model.Account;
import com.practice.service.AccountService;
import com.practice.value.HttpStatusCode;

import static com.practice.security.SecurityStaticConstants.TOKEN_PREFIX;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

		accountService.saveAccount(account);

		return new ResponseEntity<Account>(account, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseQuery<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate( // Run to loadByUserName in AccountService
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(),
						loginRequest.getPassword()
				)
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);
		Account account =  (Account) authentication.getPrincipal();
		account.setToken(jwt);
		return ResponseQuery.success("Recivce Success", account);
	}

//	@GetMapping("/logout")
//	public String getLogout() {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if (auth != null) {
//		    new SecurityContextLogoutHandler().logout(request, response, auth);
//		}
//	}

	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Account> getAllAccounts(){
		return accountService.getAllAccounts();
	}

	@GetMapping("/{email}")
	@PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	public Account findByEmail(@PathVariable String email) {
		return accountService.findByEmail(email);
	}

	@PostMapping("/autoLogin")
	public ResponseQuery<?> reciveProfileByToken(HttpServletRequest request) {
		if(request != null) {
			Account account = (Account) request.getSession().getAttribute("userInfo");
			return ResponseQuery.success("Recivce Success", account);
		}else {
			return ResponseQuery.faild("Recivce Failed", null);
		}

	}

}

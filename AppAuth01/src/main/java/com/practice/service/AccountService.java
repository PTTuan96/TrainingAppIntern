package com.practice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practice.jwt.JwtTokenProvider;
//import com.practice.auth.UserDao;
import com.practice.model.Account;
import com.practice.repository.AccountRepository;
import com.practice.repository.ProfileRepository;

import static com.practice.security.Role.*;

@Service
public class AccountService implements UserDetailsService{

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Override
	public Account loadUserByUsername(String email) throws UsernameNotFoundException {
		Account account = accountRepository.findByEmail(email);
		if(account == null) throw new UsernameNotFoundException(String.format("Username %s not found", email));
		account.setGrantedAuthority(account.getRole().getGrantedAuthorities());
		return account;
	}

	public Long saveAccount(Account account) {
			account.setRole(USER);
			return accountRepository.saveAccount(account);
	}

	public List<Account> getAllAccounts(){
		return accountRepository.getAllAccounts();
	}

	public Account findByEmail(String email) {
		return accountRepository.findByEmail(email);
	}

}

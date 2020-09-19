package com.practice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//import com.practice.auth.UserDao;
import com.practice.model.Account;
import com.practice.repository.AccountRepository;

@Service
public class AccountService implements UserDetailsService{
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Account account = accountRepository.findByEmail(email);
		if(account == null) throw new UsernameNotFoundException(String.format("Username %s not found", email)); 
		return account;
	}
	
	public Long saveAccount(Account account) {
			return accountRepository.saveAccount(account);
	}
}

package com.practice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.practice.exception.EmailAlreadyExistsException;
import com.practice.model.Account;
import com.practice.model.mapper.AccountMapper;

@Repository
public class AccountRepository {
	private final AccountMapper accountMapper;

	@Autowired
	private BCryptPasswordEncoder passwordConfig;
	public AccountRepository(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}

	public Long saveAccount(Account account) {
		account.setPassword(passwordConfig.encode(account.getPassword()));
		if(findByEmail(account.getEmail()) == null) {
			account.setUsername(account.getUsername());
		}
		else throw new EmailAlreadyExistsException("Email" + account.getUsername() + "already exists");
		return accountMapper.saveAccount(account);
	}

	public Account findByEmail(String email) {
		return accountMapper.findByEmail(email);
	}

	public Account getById(int id) {
		return accountMapper.findById(id);
	}
}

package com.practice.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.practice.model.Account;
import com.practice.model.mapper.AccountMapper;

@Repository
public class MemberRepository {
	private final AccountMapper accountMapper;

	public MemberRepository(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}

	public List<Account> getMembersEmailByRole() {
		return accountMapper.getMembersEmailByRole();
	}
	
	public Account findByEmailMember(String email) {
		return accountMapper.findByEmail(email);
	}

}

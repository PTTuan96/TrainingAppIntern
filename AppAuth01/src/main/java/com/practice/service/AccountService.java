package com.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import com.practice.auth.UserDao;
import com.practice.model.Account;
import com.practice.repository.AccountRepository;

@Service
public class AccountService{
//	private final UserDao userDao;
//
//	@Autowired
//	public AccountService(@Qualifier("fake") UserDao userDao) {
//		this.userDao = userDao;
//	}

	//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		return accountRepository.selectUserByUsername(username)
//				.orElseThrow(() ->
//					new UsernameNotFoundException(String.format("Username %s not found", username)));
//	}

	@Autowired
	private AccountRepository accountRepository;

	public Long saveAccount(Account account) {

			return accountRepository.saveAccount(account);
	}

	public Account getByUsername(String username){
		return accountRepository.findByEmail(username);
	}
//
//	@Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Account account = accountRepository.findByUsername(email);
//
//        if(account == null) new UsernameNotFoundException("User not found");
//        return account;
//    }

//    @Transactional
//    public Account loadAccountById(int id){
//        Account account = accountRepository.getById(id);
//        if(account == null) new UsernameNotFoundException("User not found");
//        return account;
//    }
}

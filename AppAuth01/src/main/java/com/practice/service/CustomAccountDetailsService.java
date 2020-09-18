package com.practice.service;

import com.practice.model.Account;
import com.practice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomAccountDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email);

        if(account == null) new UsernameNotFoundException("User not found");
        account.setEmail(email);
        return account;
    }

    @Transactional
    public Account loadAccountById(int id){
        Account account = accountRepository.getById(id);
        if(account == null) new UsernameNotFoundException("User not found");
        return account;
    }
}

package com.practice.model.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.practice.model.Account;

@Mapper
public interface AccountMapper {
//	Optional<Account> login(String username, String password);
//	Optional<Account> findByToken(String token);
	Account findById(int id);
	Account findByEmail(@Param("email") String email);
	public Long saveAccount(Account account);
	public List<Account> getMembersEmailByRole();

}

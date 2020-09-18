package com.practice.model.mapper;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

import com.practice.model.Account;
import com.practice.model.Profile;

@Mapper
public interface ProfileMapper {
	public Long saveProfile(Profile profile);

//	public Optional<Profile> findByEmail(String email);

	public Optional<Profile> findById(int id);

	public List<Profile> findAllProfile();

	public Long deleteProfile(Integer id);

	public List<Account> getMembersEmailByRole();
}

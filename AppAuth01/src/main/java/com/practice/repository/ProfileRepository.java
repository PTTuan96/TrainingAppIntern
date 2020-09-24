package com.practice.repository;

import java.util.List;
import java.util.Optional;

import com.practice.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;
import com.practice.model.Profile;
import com.practice.model.mapper.ProfileMapper;

@Repository
public class ProfileRepository {
	private final ProfileMapper profileMapper;

	public ProfileRepository(ProfileMapper memberMapper) {
		this.profileMapper = memberMapper;
	}

	public Long saveMember(Profile profile) {
		return profileMapper.saveProfile(profile);
	}

	public Profile findByEmail(String email) {
		return profileMapper.findByEmail(email);
	}

	public Profile findById(int id) {
		return profileMapper.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("Not found memberId = %d", id)));
	}

	public List<Profile> findAll() {
		return profileMapper.findAllProfile();
	}

	public Long delete(int profileId) {
		return profileMapper.deleteProfile(profileId);
	}

}

package com.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.model.Profile;
import com.practice.repository.ProfileRepository;

@Service
public class ProfileService{

	@Autowired
	private ProfileRepository profileRepository;

	public Long save(Profile profile) {
		return profileRepository.saveMember(profile);
	}

//	public Optional<Profile> findByEmail(String email) {
//		return profileRepository.findByEmail(email);
//	}

	public Profile findById(int id) {
		return profileRepository.findById(id);
	}

	public List<Profile> findAll() {
		return profileRepository.findAll();
	}
	
	public Long delete(Integer profileId) {
		return profileRepository.delete(profileId);
	}
}
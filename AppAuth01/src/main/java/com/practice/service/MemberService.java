package com.practice.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.dto.MemberInfoDTO;
import com.practice.model.Account;
import com.practice.model.Profile;
import com.practice.repository.AccountRepository;
import com.practice.repository.MemberRepository;
import com.practice.repository.ProfileRepository;
import static com.practice.security.Role.*;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper modelMapper;

	public MemberInfoDTO findByEmailMember(String email) {
		Account account = memberRepository.findByEmailMember(email);
		return modelMapper.map(account, MemberInfoDTO.class);
	}
	
	public List<MemberInfoDTO> getMembersEmailByRole(){
		List<MemberInfoDTO> members = memberRepository.getMembersEmailByRole()
				.stream().map(account -> { 
					return modelMapper.map(account, MemberInfoDTO.class); // map account to MemberDTO
				}).collect(Collectors.toList());
		return members;
	}

	@Transactional
	public Long save(Profile profile) {
		Random rand = new Random();
		Account account = new Account(
				profile.getEmail(),
				"123",
				profile.getName().toLowerCase().replace("\\s+","") + rand.nextInt(900) + 100,
				MEMBER,
				true,
				true,
				true,
				true
		);
		accountRepository.saveAccount(account);
		return profileRepository.saveMember(profile);
	}
}

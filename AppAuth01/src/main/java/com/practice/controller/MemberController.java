package com.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.dto.MemberInfoDTO;
import com.practice.model.Profile;
import com.practice.service.MemberService;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController{

	@Autowired
	private MemberService memberService;

	@GetMapping("/findAll")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<MemberInfoDTO> getAllMembers(){
		return memberService.getMembersEmailByRole();
	}

	@GetMapping("/{email}")
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public MemberInfoDTO getById(@PathVariable (value = "email") String email){
		return memberService.findByEmailMember(email);
	}
	
	@PostMapping("/create")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	public ResponseEntity<?> createMember(@RequestBody Profile profile) {
		Long member = memberService.save(profile);
		return new ResponseEntity<Long>(member, HttpStatus.CREATED);
	}
}


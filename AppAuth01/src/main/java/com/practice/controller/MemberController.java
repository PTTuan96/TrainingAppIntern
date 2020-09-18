package com.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	public List<MemberInfoDTO> getAllMembers(){
		return memberService.getMembersEmailByRole();
	}

	@PostMapping("/create")
	public ResponseEntity<?> createMember(@RequestBody Profile profile) {
		Long member = memberService.save(profile);
		return new ResponseEntity<Long>(member, HttpStatus.CREATED);
	}
}


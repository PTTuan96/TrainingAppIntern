package com.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.model.History;
import com.practice.service.HistoryService;

@RestController
@RequestMapping("/api/v1/histories")
public class HistoryController {
	@Autowired
	private HistoryService historyService;

	@GetMapping("/")
	public ResponseEntity<?> createHistory(@RequestParam(value = "teamId") Long teamId,
											@RequestParam(value = "memberId") Long memberId
											) throws Exception{
		historyService.addMemberToTeam(teamId, memberId);
		return new ResponseEntity<String>("Member with ID: " + memberId + "has join team with id: " + teamId, HttpStatus.CREATED);
	}

	@GetMapping("/membersNotInTour")
	public List<History> listMembersNotInTour(){
		return historyService.listMembers();
	}
}

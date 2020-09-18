package com.practice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.model.Tournament;

@RestController
@RequestMapping("api/v1/tournaments")
public class TournamentController {
	private static final List<Tournament> TOURNAMENTS = Arrays.asList(
		new Tournament(1, "World Cup"),
		new Tournament(2, "Expand "),
		new Tournament(3, "Private Match")
	);
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public List<Tournament> getAllTournaments(){
		return TOURNAMENTS;
	}
	
	@GetMapping(path = "/{tournamentId}")
	@PreAuthorize("hasRole('ROLE_USER','ROLE_ADMIN')")
	public Tournament getTournament(@PathVariable("tournamentId") Integer tournamentId) {
		return TOURNAMENTS.stream()
				.filter(tour -> tournamentId.equals(tour.getId()))
				.findFirst()
				.orElseThrow(() -> new IllegalStateException("Tournament " + tournamentId + "does not exitts"));
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void addTournament(@RequestBody Tournament tour) {
		System.out.print(tour);
	}
	
	@PutMapping(path = "{tourId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void updateTournament(@PathVariable Integer tourId, @RequestBody Tournament tour) {
		System.out.println(String.format("%s %s", tourId, tour));
	}
	
	@DeleteMapping(path = "{tourId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteTournament(@PathVariable Integer tourId) {
		System.out.print(tourId);
	}
	
}

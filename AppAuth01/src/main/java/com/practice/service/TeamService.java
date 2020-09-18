package com.practice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.practice.model.Team;
import com.practice.repository.TeamRepository;

@Service
public class TeamService {
	@Autowired
	private TeamRepository teamRepository;

	public Long save(Team team) {
		return teamRepository.saveTeam(team);
	}

	public List<Team> findAll() {
		return teamRepository.findAllTeam();
	}

	public Team findByName(String name) {
		return teamRepository.findByName(name);
	}

	public Team findById(int id){
		return teamRepository.findById(id);
	}

	public void deleteTeam(int id) {
		teamRepository.deleteTeam(id);
	}
}

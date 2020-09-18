package com.practice.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.practice.model.Team;
import com.practice.model.mapper.TeamMapper;

@Repository
public class TeamRepository {
	private final TeamMapper teamMapper;

	public TeamRepository(TeamMapper teamMapper) {
		super();
		this.teamMapper = teamMapper;
	}

	public Long saveTeam(Team profile) {
		return teamMapper.saveTeam(profile);
	}

	public Team findById(int id) {
		return teamMapper.findById(id);
	}

	public Team findByName(String name) {
		return teamMapper.findByName(name);
	}

	public List<Team> findAllTeam() {
		return teamMapper.findAllTeams();
	}

	public void deleteTeam(int id) {
		teamMapper.deleteTeam(id);
	}
}

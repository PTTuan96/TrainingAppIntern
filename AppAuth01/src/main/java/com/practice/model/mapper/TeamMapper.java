package com.practice.model.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.practice.model.Team;

@Mapper
public interface TeamMapper {
	public List<Team> findAllTeams();

	public Long saveTeam(Team profile);

	public void deleteTeam(int id);

	public Team findById(int id);

	public Team findByName(String name);
}

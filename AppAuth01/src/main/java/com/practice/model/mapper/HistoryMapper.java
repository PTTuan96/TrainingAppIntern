package com.practice.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.practice.model.History;

@Mapper
public interface HistoryMapper {
	public Long saveHistory(History history);
	public List<History> findMembersNotInTour();
	public History returnLatestMembers(Long idTeam, Long idMember);
}

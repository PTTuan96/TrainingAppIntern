package com.practice.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.practice.model.History;
import com.practice.model.mapper.HistoryMapper;

@Repository
public class HistoryRepository {
	private final HistoryMapper historyMapper;

	public HistoryRepository(HistoryMapper historyMapper) {
		this.historyMapper = historyMapper;
	};

	public Long saveHistory(History history) {
		return historyMapper.saveHistory(history);
	}

	public List<History> membersNotInTour() {
		return historyMapper.findMembersNotInTour();
	}

	public History returnLatestMembers(Long idTeam, Long idMember) {
		return historyMapper.returnLatestMembers(idTeam, idMember);
	}
}

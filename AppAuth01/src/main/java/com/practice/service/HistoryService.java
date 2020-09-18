package com.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.exception.DuplicateDataException;
import com.practice.model.History;
import com.practice.repository.HistoryRepository;

@Service
public class HistoryService {
	@Autowired
	private HistoryRepository historyRepository;

	public void addMemberToTeam(Long teamId, Long memberId) throws Exception {

		// val a=historyRepository.returnLatestMembers(teamId, memberId);

		if (historyRepository.returnLatestMembers(teamId, memberId) == null) {
			History history = new History();
			history.setId_member(memberId);
			history.setId_team(teamId);
			history.setId_tour(Long.valueOf(0));
			historyRepository.saveHistory(history);
		} else {
			throw new DuplicateDataException("Member has");
		}

	}

	public List<History> listMembers() {
		return historyRepository.membersNotInTour();
	}
}

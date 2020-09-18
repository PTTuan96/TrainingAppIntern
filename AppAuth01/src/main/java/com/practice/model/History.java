package com.practice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class History {
	private Long id_history;
	private Long id_member;
	private Long id_team;
	private Long id_tour;
}

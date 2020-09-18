package com.practice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {
	private int id;
	private String team_name;
	private int team_totalwin;
	private int team_totalmatch;
	private String team_type;
	private String team_description;
	private String team_logo;
	private int team_totalmember;
	private int id_tour;
}

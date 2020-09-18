package com.practice.inputform;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamForm {
	private String team_name;
	private String team_type;
	private String team_description;
	private String team_logo;
	private MultipartFile file;
}

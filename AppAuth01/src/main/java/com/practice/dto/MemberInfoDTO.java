package com.practice.dto;

import com.practice.model.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoDTO {
	private Integer id;
	private String username;
	private Profile profile;
}

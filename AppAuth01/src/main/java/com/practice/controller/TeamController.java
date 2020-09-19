package com.practice.controller;

import java.io.File;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.practice.inputform.TeamForm;
import com.practice.model.Team;
import com.practice.service.TeamService;

@RestController
@RequestMapping("api/v1/teams")
public class TeamController {
	@Autowired
	private TeamService teamService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/all")
	public List<Team> getAllTeams() {
		return teamService.findAll();
	}

//	@Autowired
//	private ServletContext servletContext;

	@PostMapping("/create")
	public ResponseEntity<?> addTeam(@ModelAttribute TeamForm teamForm) throws Exception {

		if (teamService.findByName(teamForm.getTeam_name()) != null) {
			return new ResponseEntity<String>("Team has already exsist!", HttpStatus.BAD_REQUEST);
		}
		else {
			teamForm.setTeam_logo(saveFile(teamForm.getFile()));
			Team team = modelMapper.map(teamForm, Team.class);
			Long team1 = teamService.save(team);
			return new ResponseEntity<Long>(team1, HttpStatus.CREATED);
		}
//		System.out.println("This is url" + System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/images/");
//		System.out.println(servletContext.getRealPath("/"));
	}

	@PutMapping(path = "{teamId}")
	public ResponseEntity<?> updateTeam(@PathVariable Integer teamId, @RequestBody Team team) {

		return new ResponseEntity<String>("Team with ID: '" + team.getTeam_name() + "' was edited", HttpStatus.OK);
	}

	@DeleteMapping(path = "{teamId}")
	public ResponseEntity<?> deleteTeam(@PathVariable int teamId) {
		teamService.deleteTeam(teamId);
		return new ResponseEntity<String>("Team with ID: '" + teamId + "' was deleted", HttpStatus.OK);
	}

	static String saveFile(MultipartFile multipartFile) throws Exception {
		String destination = System.getProperty("user.dir") + "/src/main/webapp/images/"
				+ multipartFile.getOriginalFilename();
		File file = new File(destination);
		multipartFile.transferTo(file);
		String urlImg = "/images/" + multipartFile.getOriginalFilename();
		return urlImg;
	}

}

//private static final List<Team> TEAMS = Arrays.asList(
//new Team(1, "A team"),
//new Team(2, "B team"),
//new Team(3, "C team")
//);

//@GetMapping
//@PreAuthorize("hasRole('ROLE_ADMIN')")
//public List<Team> getAllTeams(){
//return TEAMS;
//}
//
//
//@GetMapping(path="/{teamId}")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
//public Team getTeam(@PathVariable("teamId") Integer teamId) {
//return TEAMS.stream()
//		.filter(team -> teamId.equals(team.getId()))
//		.findFirst()
//		.orElseThrow(() -> new IllegalStateException("Team " + teamId + "does not exits"));
//}

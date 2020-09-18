package com.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.model.Profile;
import com.practice.service.ProfileService;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {
	@Autowired
	private ProfileService profileService;

	@GetMapping
	public List<Profile> allProfiles(){
		return profileService.findAll();
	}

	@GetMapping(path = "{profileId}")
	public Profile getProfileById(@PathVariable (value = "profileId") int profileId) {
		return profileService.findById(profileId);
	}

	@PostMapping
	public ResponseEntity<?> createProfile(@RequestBody Profile profile) {

		Long profile2 = profileService.save(profile);
		return new ResponseEntity<Long>(profile2, HttpStatus.CREATED);
	}

	@DeleteMapping("/{profileId}")
    public ResponseEntity<?> deleteProject(@PathVariable Integer profileId){
		profileService.delete(profileId);
        return new ResponseEntity<String>("Profile with ID: '"+profileId+"' was deleted", HttpStatus.OK);
    }
}

//@GetMapping(path = "{profileEmail}")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
//public ResponseEntity<?> getProfileByEmail(@PathVariable (value = "profileEmail") String profileEmail) {
//	profileService.findByEmail(profileEmail);
//	return new ResponseEntity<Profile>(HttpStatus.OK);
//}
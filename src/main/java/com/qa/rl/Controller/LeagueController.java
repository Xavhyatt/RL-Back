package com.qa.rl.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.rl.Exceptions.ResourceNotFoundException;
import com.qa.rl.Model.LeagueModel;
import com.qa.rl.Repositories.LeagueRepository;

@CrossOrigin
@RestController
@RequestMapping("api/")
public class LeagueController {
	
	@Autowired
	LeagueRepository leagueRepo;
	
	//Method to Create a League
	@PostMapping("league")
	public LeagueModel createLeague(@Valid @RequestBody LeagueModel lm) {
		return leagueRepo.save(lm);
	}
	
	//Method to Get a League
	@GetMapping("league/{id}")
		public LeagueModel getLeagueById(@PathVariable(value="id") Long leagueId) {
			return leagueRepo.findById(leagueId)
					.orElseThrow(() -> new ResourceNotFoundException("LeagueModel", "Id", leagueId));
	}
	
	//Method to get all Leagues
	@GetMapping("league")
	public List<LeagueModel> getAllLeagues() {
		return leagueRepo.findAll();
	}
	
	//Method to Edit a League
	@PutMapping("league/{id}")
	public LeagueModel updateLeague(@PathVariable(value = "id") Long leagueId, @Valid @RequestBody
			LeagueModel leagueDetails) {
		LeagueModel lM = leagueRepo.findById(leagueId)
				.orElseThrow(() -> new ResourceNotFoundException("League","id",leagueId));
	
		lM.setName(leagueDetails.getName());
		lM.setCountry(leagueDetails.getCountry());

		
		LeagueModel leagueData = leagueRepo.save(lM);
		return leagueData;
		
	}
	
	//Method to remove a League
	@DeleteMapping("league/{id}")
	public ResponseEntity<?> deleteLeague(@PathVariable(value="id") Long leagueId){
		LeagueModel lM = leagueRepo.findById(leagueId)
				.orElseThrow(() -> new ResourceNotFoundException("League", "id", leagueId));
		
		leagueRepo.delete(lM);
		return ResponseEntity.ok().build();
	}
	

}

package com.qa.rl.Controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.rl.Exceptions.ResourceNotFoundException;
import com.qa.rl.Model.LeagueModel;
import com.qa.rl.Model.TeamModel;
import com.qa.rl.Repositories.LeagueRepository;
import com.qa.rl.Repositories.TeamRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TeamController {
	
	@Autowired
	TeamRepository teamRepo;
	
	@Autowired
	LeagueRepository leagueRepo;
	
	//Method to Create a Team
	@PostMapping("/league/{leagueId}/team")
	public TeamModel createTeam(@PathVariable(value="leagueId") Long leagueId,
			@Valid @RequestBody TeamModel teamModel) {
		return leagueRepo.findById(leagueId).map(leagueModel -> {
			teamModel.setLeagueId(leagueModel);
			return teamRepo.save(teamModel);
		}).orElseThrow(() -> new ResourceNotFoundException("League", "id", teamModel));
	}
	
	//Method to update league team is in
	@PutMapping("/league/{leagueId}/team/{teamId}")
	public TeamModel updateTeamLeague(@PathVariable(value = "teamId") Long teamId,
			@PathVariable(value="leagueId") Long leagueId) {
		
		TeamModel teamModel = teamRepo.findById(teamId).get();
		return leagueRepo.findById(leagueId).map(leagueModel -> {
			teamModel.setLeagueId(leagueModel);
			return teamRepo.save(teamModel);
		}).orElseThrow(() -> new ResourceNotFoundException("League", "id", teamModel));
	}
	
	// Method to get a team
	@GetMapping("/team/{teamId}")
	public TeamModel getTeamByTeamId(@PathVariable(value="teamId") Long teamId, Pageable pageable) {
		return teamRepo.findById(teamId)
				.orElseThrow(() -> new ResourceNotFoundException("TeamModel", "id", teamId));
	}
	
	// Method to get a team by name
	@GetMapping("/findbyteamname/{name}")
	public List<TeamModel> getTeamsByName(@PathVariable(value="name") String name, Pageable pageable){
		List<TeamModel> team = teamRepo.findAll();
		//This needs Logic to handle spaces
		team = team.stream().filter(t -> {
			if(t.getName().toLowerCase().startsWith(name.toLowerCase())) { 
				return true;
			}
			return false;
		}).collect(Collectors.toList());
		return team;
		}
	
	//Get all teams in a League
	@GetMapping("/league/{leagueId}/team")
	public Page<TeamModel> gettAllTeamsByLeagueId(@PathVariable(value="leagueId") LeagueModel leagueId, Pageable pageable){
		return teamRepo.findByLeagueId(leagueId, pageable);
		
	}

	//Get All Teams
	@GetMapping("/team")
	public List<TeamModel> getAllTeams(){
		return teamRepo.findAll();
	}
	
	//Edit Team Details
	@PutMapping("/team/{teamId}")
	public TeamModel updateTeam(@PathVariable(value="teamId") Long teamId, @Valid @RequestBody TeamModel teamrequest) {
		return teamRepo.findById(teamId).map(team -> {
			team.setName(teamrequest.getName());
			team.setLocation(team.getLocation());
			team.setGroundName(team.getGroundName());
			team.setColours(team.getColours());
			team.setFounded(team.getFounded());
			return teamRepo.save(team);
		}).orElseThrow(() -> new ResourceNotFoundException("Team", "id", teamrequest));
	}
}


package com.qa.rl.Controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.qa.rl.Model.PlayerModel;
import com.qa.rl.Model.TeamModel;
import com.qa.rl.Repositories.LeagueRepository;
import com.qa.rl.Repositories.PlayerRepository;
import com.qa.rl.Repositories.TeamRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class PlayerController {

	@Autowired
	PlayerRepository playerRepo;
	
	@Autowired
	TeamRepository teamRepo;
	
	@Autowired
	LeagueRepository leagueRepo;
	
	
	// Method to Create Player Profile
	@PostMapping("/team/{teamId}/player")
	public PlayerModel addPlayer(@PathVariable(value="teamId") Long teamId, @Valid @RequestBody
	PlayerModel playerModel) {
		return teamRepo.findById(teamId).map(teamModel -> {
			playerModel.setTeamId(teamModel);
			return playerRepo.save(playerModel);
		}).orElseThrow(() -> new ResourceNotFoundException("Team", "id", playerModel));
				
	}
	
	// Method to Update team player is in
	@PutMapping("team/{teamId}/player/{playerId}")
	public PlayerModel updatePlayerTeam(@PathVariable(value="teamId") Long teamId, 
			@PathVariable(value="playerId") Long playerId) {
		
		PlayerModel playerModel = playerRepo.findById(playerId).get();
		return teamRepo.findById(teamId).map(teamModel -> {
			playerModel.setTeamId(teamModel);
			return playerRepo.save(playerModel);
		}).orElseThrow(() -> new ResourceNotFoundException("Team", "id", playerModel));
	}
	
	// Method to Edit Player Profile
	@PutMapping("/player/{playerId}")
	public PlayerModel updatePlayer(@PathVariable(value="playerId") Long playerId, @Valid @RequestBody
			PlayerModel playerRequest) {
		return playerRepo.findById(playerId).map(player -> {
			player.setName(playerRequest.getName());
			player.setSurname(playerRequest.getSurname());
			player.setPosition(playerRequest.getPosition());
			player.setHeight(playerRequest.getHeight());
			player.setWeight(playerRequest.getWeight());
			player.setBirth(playerRequest.getBirth());
			player.setNationality(playerRequest.getNationality());
			player.setPictureLink(playerRequest.getPictureLink());
			return playerRepo.save(player);
		}).orElseThrow(() -> new ResourceNotFoundException("Player", "id", playerRequest));
	}
	
	//Method to get Player By Id
	@GetMapping("/player/{playerId}")
	public PlayerModel getPlayerById(@PathVariable(value="playerId") Long playerId, Pageable pageable) {
		return playerRepo.findById(playerId)
				.orElseThrow(() -> new ResourceNotFoundException("PlayerModel", "id", playerId));
	}
	
	@GetMapping("/findbyplayername/{name}&{surname}")
	public List<PlayerModel> getAllPlayersByName(@PathVariable(value="name") String name,
			@PathVariable(value="surname") String surname, Pageable pageable) {
		List<PlayerModel> player = playerRepo.findAll();
		
		
		player = player.stream().filter(p -> {
			if(p.getName().toLowerCase().startsWith(name.toLowerCase())
					&& p.getSurname().toLowerCase().startsWith(surname.toLowerCase())) { 
				return true;
			}
			if(surname.equals("")) {
				if(p.getName().toLowerCase().startsWith(name.toLowerCase())
						|| p.getSurname().toLowerCase().startsWith(surname.toLowerCase())) {
					return true;
					}
				}
			return false;
		}).collect(Collectors.toList());
		return player;
	}
	
	@GetMapping("/team/{teamId}/player")
	public Page<PlayerModel> getPlayersByTeam(@PathVariable(value="teamId") TeamModel teamId, Pageable pageable){
		return playerRepo.findByTeamId(teamId, pageable);
		
	}
	
	@GetMapping("/player")
	public List<PlayerModel> getAllPlayers(){
		return playerRepo.findAll();
	}
	
	
	// Method to remove a user
	@DeleteMapping("/player/{playerId}")
	public ResponseEntity<?> deletePlayer(@PathVariable(value = "playerId") Long playerId) {
		return playerRepo.findById(playerId).map(player -> {
			playerRepo.delete(player);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("UserId", playerId.toString(), null));

	}

	
}




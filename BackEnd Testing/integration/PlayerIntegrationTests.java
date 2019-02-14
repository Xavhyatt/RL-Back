package integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.qa.rl.RlWebsite1Application;
import com.qa.rl.Model.LeagueModel;
import com.qa.rl.Model.PlayerModel;
import com.qa.rl.Model.TeamModel;
import com.qa.rl.Repositories.LeagueRepository;
import com.qa.rl.Repositories.PlayerRepository;
import com.qa.rl.Repositories.TeamRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RlWebsite1Application.class})
@AutoConfigureMockMvc
public class PlayerIntegrationTests {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private PlayerRepository playerRepo;
	
	@Autowired
	private TeamRepository teamRepo;
	
	@Autowired
	private LeagueRepository leagueRepo;
	

	
	LeagueModel league = new LeagueModel("Super League", "England");
	TeamModel team = new TeamModel("Leeds Rhinos","Leeds", "Headingley Stadium" , "Blue & Yellow" , "1900" , "pic", "hello", league);
	
	
	@Before
	public void clearDB() {
		leagueRepo.deleteAll();
		teamRepo.deleteAll();
		playerRepo.deleteAll();
	}
	
	@Test
	public void addPlayerToDatabase() throws Exception	{
		leagueRepo.save(league);
		teamRepo.save(team);
		mvc.perform(MockMvcRequestBuilders.post("/api/team/" +team.getTeamId() + "/player")
				.contentType(MediaType.APPLICATION_JSON)
				.content( "{\"name\": \"Richie\", \"surname\": \"Myler\", \"position\": \"Half Back\",\"height\": 170 ,\"weight\": 85 , \"birth\": \"1990-09-01T00:00:00.000+0000\", \"nationality\" : \"English\", \"pictureLink\" : \" Pic\" }"))
		.andExpect(status()
				.isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is("Richie")));
	}
	
	@Test
	public void findingAllPlayersFromDatabase() throws Exception 
	{
		leagueRepo.save(league);
		teamRepo.save(team);
	//	Date date = new Date("1990-09-10");
		PlayerModel player = new PlayerModel(team, "Luke","Gale","Half Back", 180, 85, null ,"English","PicLink");
		PlayerModel player2 = new PlayerModel(team, "Tom","Gale","Half Back", 180, 85, null ,"English","PicLink");
		playerRepo.save(player);
		playerRepo.save(player2);
		
		mvc.perform(get("/api/player").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].name", is("Luke"))).andExpect(status().isOk()).andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[1].name", is("Tom")));
	}
	
	@Test
	public void findingAPlayerFromDatabaseById() throws Exception 
	{
		leagueRepo.save(league);
		teamRepo.save(team);
		PlayerModel player = new PlayerModel(team, "Luke","Gale","Half Back", 180, 85, null ,"English","PicLink");
		playerRepo.save(player);
		mvc.perform(get("/api/player/"+player.getPlayerId()).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.name", is("Luke")));
	}
	
	@Test
	public void findingATeamFromDatabaseByName() throws Exception 
	{
		leagueRepo.save(league);
		teamRepo.save(team);
		PlayerModel player = new PlayerModel(team, "Luke","Gale","Half Back", 180, 85, null ,"English","PicLink");
		playerRepo.save(player);
		mvc.perform(get("/api/findbyplayername/" + player.getName() +"&" +player.getSurname()).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].name", is("Luke")));
		
		mvc.perform(get("/api/findbyplayername/" + player.getName() +"&").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].name", is("Luke")));
		
	} 
	
	@Test
	public void findingPlayerFromDatabaseByTeam() throws Exception 
	{
		leagueRepo.save(league);
		teamRepo.save(team);
		TeamModel team2 = new TeamModel("St Helens","North", "Headingley Stadium" , "Blue & Yellow" , "1900" , "pic","website", league);
		teamRepo.save(team2);
		PlayerModel player = new PlayerModel(team, "Luke","Gale","Half Back", 180, 85, null ,"English","PicLink");
		PlayerModel player2 = new PlayerModel(team2, "Tom","Gale","Half Back", 180, 85, null ,"English","PicLink");
		playerRepo.save(player);
		playerRepo.save(player2);
		
		mvc.perform(get("/api/team/" + team.getTeamId() + "/player").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].name", is("Luke")));
	}
	
	
	@Test
	public void deleteAPlayerFromTheDatabase() throws Exception{
		leagueRepo.save(league);
		teamRepo.save(team);
		PlayerModel player = new PlayerModel(team, "Luke","Gale","Half Back", 180, 85, null ,"English","PicLink");
		playerRepo.save(player);
		mvc.perform(delete("/api/player/"+player.getPlayerId()).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void editATeamInTheDatabase() throws Exception	{
		leagueRepo.save(league);
		TeamModel team2 = new TeamModel("St Helens","North", "Headingley Stadium" , "Blue & Yellow" , "1900" , "pic","website", league);
		teamRepo.save(team2);
		teamRepo.save(team);
		PlayerModel player = new PlayerModel(team, "Luke","Gale","Half Back", 180, 85, null ,"English","PicLink");
		playerRepo.save(player);
	
		mvc.perform(put("/api/team/"+team2.getTeamId()+"/player/"+player.getPlayerId()).contentType(MediaType.APPLICATION_JSON)
		.content( "{\"name\": \"Richie\", \"surname\": \"Myler\", \"position\": \"Half Back\",\"height\": 170 ,\"weight\": 85 , \"birth\": \"1990-09-01T00:00:00.000+0000\", \"nationality\" : \"English\", \"pictureLink\" : \" Pic\" }" )).andExpect(status()
				.isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.name", is("Richie")));
		mvc.perform(get("/api/team/"+team2.getTeamId() + "/player").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].name", is("Richie")));
		
	}
	
}

package integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.qa.rl.Model.TeamModel;
import com.qa.rl.Repositories.LeagueRepository;
import com.qa.rl.Repositories.TeamRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RlWebsite1Application.class})
@AutoConfigureMockMvc
public class TeamIntegrationTests {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private TeamRepository teamRepo;
	
	@Autowired
	private LeagueRepository leagueRepo;
	
	LeagueModel league = new LeagueModel("Super League", "England");
	TeamModel team = new TeamModel("Leeds Rhinos","Leeds", "Headingley Stadium" , "Blue & Yellow" , "1900" , "pic", league);
	
	
	@Before
	public void clearDB() {
		leagueRepo.deleteAll();
	}

	@Test
	public void addTeamToDatabase() throws Exception	{
		leagueRepo.save(league);
		mvc.perform(MockMvcRequestBuilders.post("/api/league/" +league.getLeagueId() + "/team")
				.contentType(MediaType.APPLICATION_JSON)
				.content( "{\"name\": \"Castleford Tigers\",\"location\": \"Castleford\",\"groundName\": \"Mend-a-Hose Stafium\",\"colours\": \"Amber & Black\",\"founded\": \"1926\",\"logoLink\": \"https://i.imgur.com/SdSbAvc.png\" }"))
		.andExpect(status()
				.isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is("Castleford Tigers")));
	}
	
/*	@Test
	public void editATeamsLeagueInTheDatabase() throws Exception	{
		leagueRepo.save(league);
		teamRepo.save(team);
		String leagueId = league.getLeagueId().toString();
		
		
		String id = mvc.perform(get("/api/team").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
		int index1 = id.indexOf(":") + 1;
		int index2 = id.indexOf(",");
		String teamId = id.substring(index1, index2);
		mvc.perform(put("/api/league/"+leagueId+"/team/"+teamId).contentType(MediaType.APPLICATION_JSON).
		.andExpect(status()
				.isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.leagueId", is(leagueId)));
	}*/

	@Test
	public void findingAllTeamsFromDatabase() throws Exception 
	{
		leagueRepo.save(league);
		teamRepo.save(team);
		teamRepo.save(new TeamModel("St Helens","North", "Headingley Stadium" , "Blue & Yellow" , "1900" , "pic", league));
		mvc.perform(get("/api/team").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].name", is("Leeds Rhinos"))).andExpect(status().isOk()).andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[1].name", is("St Helens")));
	}
	
	@Test
	public void findingATeamFromDatabaseById() throws Exception 
	{
		leagueRepo.save(league);
		teamRepo.save(team);
		teamRepo.save(new TeamModel("St Helens","North", "Headingley Stadium" , "Blue & Yellow" , "1900" , "pic", league));
		mvc.perform(get("/api/team/"+team.getTeamId()).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.name", is("Leeds Rhinos")));
	}
	
	
	@Test
	public void findingATeamFromDatabaseByName() throws Exception 
	{
		leagueRepo.save(league);
		teamRepo.save(team);
		teamRepo.save(new TeamModel("St Helens","North", "Headingley Stadium" , "Blue & Yellow" , "1900" , "pic", league));
		mvc.perform(get("/api/findbyteamname/Leeds").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.content.[0]name", is("Leeds Rhinos")));
	}
	
	@Test
	public void findingTeamsFromDatabaseByLeague() throws Exception 
	{
		leagueRepo.save(league);
		teamRepo.save(team);
		teamRepo.save(new TeamModel("St Helens","North", "Headingley Stadium" , "Blue & Yellow" , "1900" , "pic", league));
		mvc.perform(get("/api/league/"+league.getLeagueId() + "/team").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].name", is("Leeds Rhinos")));
	}
	
}

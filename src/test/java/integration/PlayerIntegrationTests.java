//package integration;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Date;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.qa.rl.RlWebsite1Application;
//import com.qa.rl.Model.LeagueModel;
//import com.qa.rl.Model.PlayerModel;
//import com.qa.rl.Model.TeamModel;
//import com.qa.rl.Repositories.LeagueRepository;
//import com.qa.rl.Repositories.PlayerRepository;
//import com.qa.rl.Repositories.TeamRepository;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {RlWebsite1Application.class})
//@AutoConfigureMockMvc
//public class PlayerIntegrationTests {
//	
//	@Autowired
//	private MockMvc mvc;
//	
//	@Autowired
//	private PlayerRepository playerRepo;
//	
//	@Autowired
//	private TeamRepository teamRepo;
//	
//	@Autowired
//	private LeagueRepository leagueRepo;
//	
//
//	
//	LeagueModel league = new LeagueModel("Super League", "England");
//	TeamModel team = new TeamModel("Leeds Rhinos","Leeds", "Headingley Stadium" , "Blue & Yellow" , "1900" , "pic", "hello", league);
//	
//	
//	@Before
//	public void clearDB() {
//		leagueRepo.deleteAll();
//	}
//	
//	@Test
//	public void addPlayerToDatabase() throws Exception	{
//		leagueRepo.save(league);
//		teamRepo.save(team);
//		mvc.perform(MockMvcRequestBuilders.post("/api/team/" +team.getTeamId() + "/player")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content( "{\"name\": \"Richie\", \"surname\": \"Myler\", \"position\": \"Half Back\",\"height\": 170 ,\"weight\": 85 , \"birth\": \"1990-09-01T00:00:00.000+0000\", \"nationality\" : \"English\", \"pictureLink\" : \" Pic\" }"))
//		.andExpect(status()
//				.isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//				.andExpect(jsonPath("$.name", is("Richie")));
//	}
//	
//	@Test
//	public void findingAllPlayersFromDatabase() throws Exception 
//	{
//		leagueRepo.save(league);
//		teamRepo.save(team);
//		Date date = new Date("1990-09-10");
//		PlayerModel player = new PlayerModel(team, "Luke","Gale","Half Back", 180, 85, date ,"English","PicLink");
//		PlayerModel player2 = new PlayerModel(team, "Tom","Gale","Half Back", 180, 85, date ,"English","PicLink");
//		playerRepo.save(player);
//		playerRepo.save(player2);
//		
//		mvc.perform(get("/api/team").contentType(MediaType.APPLICATION_JSON))
//		.andExpect(status().isOk()).andExpect(content()
//				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//		.andExpect(jsonPath("$[0].name", is("Richie"))).andExpect(status().isOk()).andExpect(content()
//				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//		.andExpect(jsonPath("$[1].name", is("Tom")));
//	}
//	
//	
//}

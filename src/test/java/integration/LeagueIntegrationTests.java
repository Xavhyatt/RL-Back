//package integration;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
//
//import com.qa.rl.RlWebsite1Application;
//import com.qa.rl.Model.LeagueModel;
//import com.qa.rl.Repositories.LeagueRepository;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {RlWebsite1Application.class})
//@AutoConfigureMockMvc
//public class LeagueIntegrationTests {
//	
//	@Autowired
//	private MockMvc mvc;
//	
//	@Autowired
//	private LeagueRepository leagueRepo;
//	
//	@Before
//	public void clearDB() {
//		leagueRepo.deleteAll();
//	}
//	
//	@Test
//	public void addLeagueToDatabase() throws Exception	{
//		mvc.perform(MockMvcRequestBuilders.post("/api/league")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content("{\"name\" : \"Super League\", \"country\" : \"England\"}"))
//		.andExpect(status()
//				.isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//				.andExpect(jsonPath("$.name", is("Super League")));
//	}
//	
//	
//
//	@Test
//	public void findingALeagueFromDatabase() throws Exception 
//	{
//		leagueRepo.save(new LeagueModel("Super League", "England"));
//		String id = mvc.perform(get("/api/league").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
//		int index1 = id.indexOf(":") + 1;
//		int index2 = id.indexOf(",");
//		String leagueId = id.substring(index1, index2);
//		mvc.perform(get("/api/league/"+leagueId).contentType(MediaType.APPLICATION_JSON))
//		.andExpect(status().isOk()).andExpect(content()
//				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//		.andExpect(jsonPath("$.name", is("Super League")));
//	}
//	
//	@Test
//	public void findingAllLeaguesFromDatabase() throws Exception 
//	{
//		leagueRepo.save(new LeagueModel("Super League", "England"));
//		leagueRepo.save(new LeagueModel("Championship", "England"));
//		mvc.perform(get("/api/league").contentType(MediaType.APPLICATION_JSON))
//		.andExpect(status().isOk()).andExpect(content()
//				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//		.andExpect(jsonPath("$[0].name", is("Super League"))).andExpect(status().isOk()).andExpect(content()
//				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//		.andExpect(jsonPath("$[1].name", is("Championship")));
//	}
//	
//	@Test
//	public void editALeagueInTheDatabase() throws Exception	{
//		leagueRepo.save(new LeagueModel("Super League", "England"));
//		String id = mvc.perform(get("/api/league").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
//		int index1 = id.indexOf(":") + 1;
//		int index2 = id.indexOf(",");
//		String leagueId = id.substring(index1, index2);
//		mvc.perform(put("/api/league/"+leagueId).contentType(MediaType.APPLICATION_JSON)
//		.content("{\"name\" : \"NRL\", \"country\" : \"Australia\"}")).andExpect(status()
//				.isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//		.andExpect(jsonPath("$.name", is("NRL")));
//	}
//	
//	@Test
//	public void deleteALeagueFromTheDatabase() throws Exception{
//		leagueRepo.save(new LeagueModel("Super League", "England"));
//		String id = mvc.perform(get("/api/league").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
//		int index1 = id.indexOf(":") + 1;
//		int index2 = id.indexOf(",");
//		String leagueId = id.substring(index1, index2);
//		mvc.perform(delete("/api/league/"+leagueId).contentType(MediaType.APPLICATION_JSON))
//		.andExpect(status().isOk());
//	}
//	
//
//}

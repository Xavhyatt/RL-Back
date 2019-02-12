//package repo;
//
//import static org.junit.Assert.assertTrue;
//
//import java.util.Date;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.qa.rl.RlWebsite1Application;
//import com.qa.rl.Model.LeagueModel;
//import com.qa.rl.Model.PlayerModel;
//import com.qa.rl.Model.TeamModel;
//import com.qa.rl.Repositories.PlayerRepository;
//
//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes=RlWebsite1Application.class)
////@SpringBootTest(classes = { RlWebsite1Application.class})
//@DataJpaTest
//public class PlayerRepositoryTest {
//
//	@Autowired
//	private TestEntityManager entityManager;
//	
//	@Autowired
//	private PlayerRepository playerRepo;
//	
//	@Test
//	public void retrieveByIdTest() {
//		LeagueModel league = new LeagueModel("Super League", "England");
//		entityManager.persist(league);
//		TeamModel team = new TeamModel( "Leeds Rhinos", "Leeds", "Headingley Stadium", "Blue & Yellow", "1890", "Logo", "hello" , league );
//		entityManager.persist(team);
//		Date date = new Date("1990-09-10");
//		PlayerModel player = new PlayerModel(team, "Luke","Gale","Half Back", 180, 85, date ,"English","PicLink");
//		
//		entityManager.flush();SA
//		assertTrue(playerRepo.findById(player.getPlayerId()).isPresent());
//	}
//}

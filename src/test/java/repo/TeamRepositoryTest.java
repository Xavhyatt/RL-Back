package repo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.rl.RlWebsite1Application;
import com.qa.rl.Model.LeagueModel;
import com.qa.rl.Model.TeamModel;
import com.qa.rl.Repositories.TeamRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { RlWebsite1Application.class})
@DataJpaTest
public class TeamRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private TeamRepository teamRepo;

	
	@Test
	public void retrieveByIdTest() {
		LeagueModel league = new LeagueModel("Super League", "England");
		entityManager.persist(league);
		TeamModel team = new TeamModel( "Leeds Rhinos", "Leeds", "Headingley Stadium", "Blue & Yellow", "1890", "Logo" , league );
		entityManager.persist(team);
		entityManager.flush();
		assertTrue(teamRepo.findById(team.getTeamId()).isPresent());
	}
	
}

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
import com.qa.rl.Repositories.LeagueRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { RlWebsite1Application.class})
@DataJpaTest
public class LeagueRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private LeagueRepository leagueRepo;
	
	@Test
	public void retrieveByIdTest() {
		LeagueModel leagueModelTest = new LeagueModel("Super League", "England");
		entityManager.persist(leagueModelTest);
		entityManager.flush();
		assertTrue(leagueRepo.findById(leagueModelTest.getLeagueId()).isPresent());
	}

}

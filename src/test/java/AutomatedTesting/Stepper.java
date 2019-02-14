package AutomatedTesting;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Pages.HomePage;
import Pages.TeamProfilePage;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class Stepper {

	ExtentReports report;
	ExtentTest test;
	final private  String driverType = "webdriver.chrome.driver";
	final private String fileLoc = "C:\\Users\\Admin\\Desktop\\driver\\chromedriver.exe";
	WebDriver driver = null;
	String articleURL;

	@Before
	public void setUp() throws IOException {
	
		Runner.counter++;
		report = Runner.report;
		test = report.startTest("RL Website Testing " + Runner.counter);
		System.setProperty(driverType, fileLoc);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@After
	public void tearDown()  {
		
		driver.quit();
		report.endTest(test);
		report.flush();	
	}

	@Given("^that you are on the home screen$")
	public void that_you_are_on_the_home_screen() {
		test.log(LogStatus.INFO, "Opened RL Website home page");
		driver.get(Constants.getWebsiteurl());
	}
	
	@When("^the a news article is clicked$")
	public void the_a_news_article_is_clicked()   {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		   articleURL = homePage.goToArticle(driver);
		   test.log(LogStatus.INFO, "News article clicked");
	}

	@Then("^I am taken to the full article$")
	public void i_am_taken_to_the_full_article() throws Throwable {
		if(articleURL.equals(driver.getCurrentUrl())) {
			HelperMethods.screenshot(driver);
			test.log(LogStatus.PASS, "Taken to the news article source" + test.addScreenCapture(HelperMethods.destination1));
		} else {
			HelperMethods.screenshot(driver);
			test.log(LogStatus.FAIL, "Not taken to news article source" + test.addScreenCapture(HelperMethods.destination1));
		}
	    Assert.assertEquals(articleURL, driver.getCurrentUrl());
	}

	@When("^the a team is clicked on the navbar$")
	public void the_a_team_is_clicked_on_the_navbar()  {
	   HomePage homePage = PageFactory.initElements(driver, HomePage.class);
	   homePage.navToCastlefordPage(driver);
	   test.log(LogStatus.INFO, "Navbar team clicked");
	    
	}

	@Then("^I am on the correct teams profile page$")
	public void i_am_on_the_correct_teams_profile_page() throws Throwable {
		 TeamProfilePage teamPage = PageFactory.initElements(driver, TeamProfilePage.class);
		 
		 if(teamPage.checkTeamName().equals("Castleford Tigers")) {
				HelperMethods.screenshot(driver);
				test.log(LogStatus.PASS, "Taken to the Castleford Tigers Profile Page" + test.addScreenCapture(HelperMethods.destination1));
			} else {
				HelperMethods.screenshot(driver);
				test.log(LogStatus.FAIL, "Navigation to Castleford Profile page failed" + test.addScreenCapture(HelperMethods.destination1));
			}
		 
		 Assert.assertEquals("Castleford Tigers", teamPage.checkTeamName());
	    
	}

	@When("^I search for a team using the search bar$")
	public void i_search_for_a_team_using_the_search_bar()  {
		   HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		   homePage.searchToCastlefordPage(driver);
		   test.log(LogStatus.INFO, "Searched using search bar");

	}
	
	@Given("^that you are on the Player profile page$")
	public void that_you_are_on_the_Player_profile_page()  {
		test.log(LogStatus.INFO, "Opened RL Website home page");
		driver.get(Constants.getWebsiteurl());
		 HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		   homePage.navToCastlefordPage(driver);
		   test.log(LogStatus.INFO, "On Team Profile Page");
	}

	@When("^a name is entered in the filter bar$")
	public void a_name_is_entered_in_the_filter_bar() {
		TeamProfilePage teamPage = PageFactory.initElements(driver, TeamProfilePage.class);
		teamPage.filterRoster("Luke Gale");
		   test.log(LogStatus.INFO, "Luke Gale entered on filter bar");
	  
	}

	@Then("^the player is shown$")
	public void the_player_is_shown() {
		TeamProfilePage teamPage = PageFactory.initElements(driver, TeamProfilePage.class);
		 if(teamPage.checkPlayer1(driver).equals("Luke Gale")) {
				HelperMethods.screenshot(driver);
				test.log(LogStatus.PASS, "Luke Gale is shown" + test.addScreenCapture(HelperMethods.destination1));
			} else {
				HelperMethods.screenshot(driver);
				test.log(LogStatus.FAIL, "Filter failed" + test.addScreenCapture(HelperMethods.destination1));
			}
		 
		 Assert.assertEquals("Luke Gale", teamPage.checkPlayer1(driver));
	}





}

package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class TeamProfilePage {
	
	@FindBy(id="teamName")
	private WebElement teamName;
	
	@FindBy(xpath="//*[@id=\"teamProfile\"]/div/div[3]/div/div[1]/div/h4")
	private WebElement player1;
	
	@FindBy(id="filter")
	private WebElement filterBar;

	
	
	
	public String checkTeamName() {
		return teamName.getText();
	}
	
	public String checkPlayer1(WebDriver driver) {
		teamName.click();
		Actions action = new Actions(driver);
		for (int i =0; i<20; i++) {
			action.sendKeys(Keys.DOWN).perform();
			}
		return player1.getText();
	}
	
	public void filterRoster(String name) {
		filterBar.sendKeys(name);
	}
	
}

package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TeamProfilePage {
	
	@FindBy(id="teamName")
	private WebElement teamName;

	
	
	
	public String checkTeamName() {
		return teamName.getText();
	}
}

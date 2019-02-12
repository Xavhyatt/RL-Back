package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	@FindBy(id="superleaguedrop")
	private WebElement slDrop;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div[1]/span/div/nav/div/ul/li[3]/div/div/a[1]/button")
	private WebElement navCastleford;	
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div[1]/span/div/nav/form/input")
	private WebElement searchBar;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div[1]/span/div/nav/form/div/a/button")
	private WebElement searchResult1;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[1]/div/div[1]/div[2]/h4/a")
	private WebElement newsArticle1;

	

	
	public void navToCastlefordPage(WebDriver driver) { 
		slDrop.click();
		WebElement dynamicEl = (new WebDriverWait(driver, 10)
				.until(ExpectedConditions.elementToBeClickable(navCastleford)));
		dynamicEl.click();
	}
	
	public void searchToCastlefordPage(WebDriver driver) {
		searchBar.click();
		searchBar.sendKeys("Castleford");
		WebElement dynamicEl = (new WebDriverWait(driver, 10)
				.until(ExpectedConditions.elementToBeClickable(searchResult1)));
		dynamicEl.click();
	}
	
	public String goToArticle(WebDriver driver) {
		Actions action = new Actions(driver);
		for (int i =0; i<20; i++) {
			action.sendKeys(Keys.DOWN).perform();
			}
		String articleName = newsArticle1.getAttribute("href");
		newsArticle1.click();
		return articleName;
	}
	
}

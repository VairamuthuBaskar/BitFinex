/*	Please write an automated test for the following scenario:

			· I visit the homepage (https://www.bitfinex.com/)
			· Then I click on the ticker table Search icon.
			· search for: “Unus Sed Leo”.
			· click on the search result.
			· Assert that the page URL is: https://trading.bitfinex.com/t/LEO:USD?demo=true
*/
package DemoPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BitFinex 
{

	public static void main(String[] args) throws InterruptedException 
	{
		String applicationURL = "https://www.bitfinex.com/";
		String searchValue = "Unus Sed Leo";
		String actualURL;
		String expectedURL = "https://trading.bitfinex.com/t/LEO:USD?demo=true";
		String geckoDriverPath = "C:\\Users\\Kavin\\Desktop\\KA\\geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", geckoDriverPath);
		
		WebDriver driver = new FirefoxDriver();	
		driver.get(applicationURL);
		driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
		WebDriverWait wait =  new WebDriverWait(driver, 30);
//		Thread.sleep(5000);
		WebElement searchIcon = driver.findElement(By.xpath("//a[@class='landing-tickers__search']/span"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,50)");
		Thread.sleep(3000);
		js.executeScript("arguments[0].scrollIntoView(true);", searchIcon);
		js.executeScript("arguments[0].click();", searchIcon);
		WebElement search_textBox = driver.findElement(By.xpath("//div[@class='bp3-input-group landing-tickers__search-group']/input"));
		search_textBox.sendKeys(searchValue,Keys.ENTER);
		js.executeScript("window.scrollBy(0,100)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='landing-tickers__wrap-table landing-tickers__trading-table']/div[2]/table/tbody/tr/td")));
		WebElement filteredRow = driver.findElement(By.xpath("//div[@class='landing-tickers__wrap-table landing-tickers__trading-table']/div[2]/table/tbody/tr/td"));
		Actions act = new Actions(driver);
		act.moveToElement(filteredRow).click().build().perform();
		js.executeScript("arguments[0].click();", filteredRow);
		Thread.sleep(5000);
		actualURL = driver.getCurrentUrl();
		
		System.out.println("Actual URL: " + actualURL);
		if (actualURL.equals(expectedURL))
		{
			System.out.println("PASS - Actual URL matches with the Expected URL");
		}
		else
		{
			System.out.println("FAIL - Actual URL doesnot match with the Expected URL");
		}


	}

}

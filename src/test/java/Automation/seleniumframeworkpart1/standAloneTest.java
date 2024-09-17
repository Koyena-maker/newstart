package Automation.seleniumframeworkpart1;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class standAloneTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	    driver.findElement(By.id("userEmail")).sendKeys("rup123@yopmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Host@1234");
		driver.findElement(By.name("login")).click();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@class='card-body']"))));
		List <WebElement> names=driver.findElements(By.xpath("//*[@class='card-body']"));
		WebElement element=names.stream().filter(name->name.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("IPHONE 13 PRO")).findAny().orElse(null);
		element.findElement(By.cssSelector("[class='card-body'] button:last-of-type")).click();
		WebElement element1=names.stream().filter(name->name.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("ADIDAS ORIGINAL")).findAny().orElse(null);
		wait.until(ExpectedConditions.visibilityOf(element1));
		element1.findElement(By.cssSelector("[class='card-body'] button:last-of-type")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink=\"/dashboard/cart\"]")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[class='cartSection'] h3"))));
		List <WebElement> lists=driver.findElements(By.cssSelector("[class=\"cartSection\"]"));
		Boolean match=lists.stream().anyMatch(itemneeded->itemneeded.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase("ADIDAS ORIGINAL"));
	    System.out.println(match);
	    Actions checkout=new Actions(driver);
	    checkout.moveToElement(driver.findElement(By.cssSelector(".totalRow button"))).click().build().perform();
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[placeholder='Select Country']"))));
	    checkout.sendKeys( driver.findElement(By.cssSelector("[placeholder=\"Select Country\"]")), "Bri").build().perform();
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
	    List <WebElement> countries=driver.findElements(By.cssSelector(".ta-results button span"));
	    WebElement cou=countries.stream().filter(country->country.getText().equalsIgnoreCase("british indian ocean territory")).findAny().orElse(null);
	    cou.click();
	    driver.findElement(By.cssSelector(".action__submit")).click();
	    System.out.println("Orders ids are listed below-");
	    List <WebElement> ids=driver.findElements(By.xpath("//tr[@class=\"ng-star-inserted\"]/td/label"));
	    for(int i=0;i<ids.size();i++)
	    {
	    	System.out.println(ids.get(i).getText());
	    }
	    String message=driver.findElement(By.cssSelector(".hero-primary")).getText();
	    System.out.println(message);
	    Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
	    driver.close();
	   
	}

}

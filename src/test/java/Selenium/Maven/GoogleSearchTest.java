package Selenium.Maven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GoogleSearchTest {
	WebDriver driver;

	@Test
	public void GoogleSearchDemo() throws InterruptedException {

		System.out.println("Test");
		driver.get("https://www.google.com/");
		driver.findElement(By.xpath("//input[@title='Search']")).sendKeys("test");
		driver.findElement(By.xpath("//input[@value='Google Search']")).submit();
		;
		Thread.sleep(3000);
		String totResults = driver.findElement(By.xpath("//div[@id='result-stats']")).getText();
		System.out.println("Total results found is :" + totResults);

	}

	@BeforeSuite
	@Parameters({ "browser" })
	public void beforeTest(String browser) {
		
		String broName = browser;
		System.out.println("Configured Browser :" + broName);
		switch (broName) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "IE":
			WebDriverManager.iedriver().setup();
			InternetExplorerOptions capabilities = new InternetExplorerOptions();
			capabilities.ignoreZoomSettings();
			driver = new InternetExplorerDriver(capabilities);
			break;
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterSuite
	public void afterTest() {
		System.out.println("Completed All");
		driver.quit();

	}

}

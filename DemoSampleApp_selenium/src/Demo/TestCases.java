package Demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

public class TestCases {
	public WebDriver driver;

	@BeforeMethod
	public void setup() {
		String hostname = System.getProperty("hostname");
		String port = System.getProperty("port");
		String context = System.getProperty("context");
		driver = new HtmlUnitDriver();
		driver.get("http://" + hostname + ":" + port + "/" + context);
	}

	// Test to pass as to verify listeners .
	@Test
	public void VerifyTitle() {

		String welcometext = driver.getTitle();
		Assert.assertEquals(welcometext, "DevOps Tool", "Text not found");
	}

	@Test
	public void VerifyHomePage() {

		String welcometext = driver.findElement(By.xpath("//*[@class = 'container']/h1")).getText();
		Assert.assertEquals(welcometext, "DevOps Sample Application", "Text not found");
	}
	// Forcefully failed this test as to verify listener.
	@AfterMethod
	public void AfterTest() {
		driver.close();
	}
}
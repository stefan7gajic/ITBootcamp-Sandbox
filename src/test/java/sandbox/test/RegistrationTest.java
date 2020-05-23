package sandbox.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import sandbox.pageObject.LoginPage;
import sandbox.pageObject.RegistrationPage;
import sandbox.readingFromTxtFile.ReadingFromTxtFile;

public class RegistrationTest {
	
	WebDriver driver;
	RegistrationPage registrationPage;
	LoginPage loginPage;
	
	
	@BeforeClass
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void logInUser() {
		driver.get(LoginPage.LOG_URL);
		loginPage = new LoginPage(driver, ReadingFromTxtFile.readXPaths());
		loginPage.typeUsername("nlowne16");
		loginPage.typePassword("DM3WIRaraTep");
		loginPage.clickOnLoginButton();
		registrationPage = new RegistrationPage(driver, ReadingFromTxtFile.readXPaths());
	}
 
	@Test (priority=1)
	public void registerOneUser() {
		
		registrationPage.registerUser("milmil@gmail.com", "Milomilo8", "Milmil123", "Milmil123");
		
		Assert.assertEquals(registrationPage.updateUserText(), "Milomilo8");	
	}
	
	
	@Test (priority=2)
	public void registerUserWithoutEmail() {
		registrationPage.registerUser("", "Milomilo12", "Milmil123", "Milmil123");
		
		Assert.assertEquals(registrationPage.invalidEmail(), "Invalid Email Address");
	}
	
	@Test (priority=3)
	public void registerUserWithoutUsername() {
		registrationPage.registerUser("milmil1@gmail.com", "", "Milmil123", "Milmil123");
		
		Assert.assertEquals(registrationPage.invalidUsername(), "Invalid Username");	
	}
	
	@Test (priority=4)
	public void registerUserWithoutPassword() {
		registrationPage.registerUser("milmil2@gmail.com", "Milomilo123", "", "Milmil123");
		
		Assert.assertTrue(registrationPage.invalidPassword().contains("Invalid Password")); 
	}
	
	@Test (priority=5)
	public void registerUserWithoutConfirmPassword() {
		registrationPage.registerUser("milmil3@gmail.com", "Milomilo1", "Milmil123", "");
		
		Assert.assertEquals(registrationPage.noPasswordMatch(), "Passwords did not match.");	
	}
	
	@AfterMethod
	public void logOutUser() {
		loginPage.clickOnNewUserAccountButton();
		loginPage.clickOnlogOutButton();
	}

	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
	
}

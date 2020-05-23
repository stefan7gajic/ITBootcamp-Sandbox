package sandbox.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import sandbox.pageObject.LoginPage;
import sandbox.readingFromTxtFile.ReadingFromTxtFile;

public class LoginTest {
	
		WebDriver driver;
		LoginPage loginPage;
		
		@BeforeClass
		public void openBrowser() {
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}

		@BeforeMethod
		public void logPage() {
			loginPage = new LoginPage(driver, ReadingFromTxtFile.readXPaths());
			driver.get(LoginPage.LOG_URL);
		}


		@Test(priority = 1)
		public void loginUserWithoutRegistration() {
			loginPage.typeUsername("NekoIme");
			loginPage.typePassword("Sifra123");
			loginPage.clickOnLoginButton();
			
			Assert.assertEquals(loginPage.incorrectUsernameOrPasswordMessage(), "Incorrect username or password.");
		}
		
		@Test(priority = 2)
		public void loginOneUser() {
			loginPage.typeUsername("nlowne16");
			loginPage.typePassword("DM3WIRaraTep");
			loginPage.clickOnLoginButton();
			loginPage.clickOnNewUserAccountButton();
			Assert.assertEquals(loginPage.logedUsername(), "nlowne16");
			loginPage.clickOnlogOutButton();
		}
		
		@Test(priority = 3)
		public void login30Users() {
			
			for (int i = 1; i <= 30; i++) {
				loginPage.userLogIn(i);
				loginPage.clickOnNewUserAccountButton();
				Assert.assertEquals(loginPage.logedUsername(), loginPage.desiredData(i, 1, "src/test/resources/users.xlsx"));
				loginPage.clickOnlogOutButton();
			}
			
		}
		
		@AfterClass
		public void closeBrowser() {
			driver.quit();
		}
}

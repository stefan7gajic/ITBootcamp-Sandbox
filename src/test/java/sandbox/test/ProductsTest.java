package sandbox.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import sandbox.pageObject.LoginPage;
import sandbox.pageObject.ProductsPage;
import sandbox.readingFromTxtFile.ReadingFromTxtFile;

public class ProductsTest {
	WebDriver driver;
	LoginPage loginPage;
	ProductsPage productsPage;

	@BeforeTest
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@BeforeClass
	public void logInUser() {
		driver.get(LoginPage.LOG_URL);
		loginPage = new LoginPage(driver, ReadingFromTxtFile.readXPaths());
		loginPage.typeUsername("nlowne16");
		loginPage.typePassword("DM3WIRaraTep");
		loginPage.clickOnLoginButton();
	}
	
	@BeforeMethod
	public void prodPage() {
		productsPage = new ProductsPage(driver, ReadingFromTxtFile.readXPaths());
		
	}
	
	@Test(priority=1)
	public void addProducts() {
		
		for (int i = 1; i <= 5; i++) {
			productsPage.addNewProduct(i);
			Assert.assertEquals(productsPage.successfullyAddedProductMessage(), "Update successful");
		}
	}
	
	@Test (priority=2)
	public void increasePrice() {
		productsPage.clickProductsCard();
		productsPage.clickEditProducts();
		productsPage.increasePrice(100.00);
		productsPage.clickSaveProductChange();
		
		Assert.assertEquals(productsPage.successfullySavedProductChangesMessage(), "All updates succeeded");
	}

	@AfterClass
	public void logOutUser() {
		loginPage.clickOnNewUserAccountButton();
		loginPage.clickOnlogOutButton();
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
}

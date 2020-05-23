package sandbox.pageObject;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import sandbox.readingFromXlsxFile.ReadingFromXlsxFile;



public class LoginPage extends ReadingFromXlsxFile{
	
	WebDriver driver;
	Map<String, String> xPaths;
	
	public static String LOG_URL = "https://sandbox.2checkout.com/sandbox";
	public static String USERNAME = "USERNAME";
	public static String PASSWORD = "PASSWORD";
	public static String LOGINBUTTON = "LOGINBUTTON";
	public static String INCCORECTUSERNAMEORPASSWORD = "INCCORECTUSERNAMEORPASSWORD";
	public static String NEWUSERACCOUNTBUTTON = "NEWUSERACCOUNTBUTTON";
	public static String LOGEDUSERNAME = "LOGEDUSERNAME";
	public static String LOGOUTBUTTON = "LOGOUTBUTTON";

	public LoginPage(WebDriver driver, Map<String, String> xPaths) {
		this.driver = driver;
		this.xPaths = xPaths;
	}

	public void typeUsername(String username) {
		driver.findElement(By.xpath(xPaths.get(USERNAME))).sendKeys(username);
	}

	public void typePassword(String password) {
		driver.findElement(By.xpath(xPaths.get(PASSWORD))).sendKeys(password);
	}

	public void clickOnLoginButton() {
		driver.findElement(By.xpath(xPaths.get(LOGINBUTTON))).click();
	}
	
	public String incorrectUsernameOrPasswordMessage() {
		return driver.findElement(By.xpath(xPaths.get(INCCORECTUSERNAMEORPASSWORD))).getText();
		}
	
	public void clickOnNewUserAccountButton() {
		driver.findElement(By.xpath(xPaths.get(NEWUSERACCOUNTBUTTON))).click();
	}
	
	public String logedUsername() {
		return driver.findElement(By.xpath(xPaths.get(LOGEDUSERNAME))).getText();
	}
	
	public void clickOnlogOutButton() {
		driver.findElement(By.xpath(xPaths.get(LOGOUTBUTTON))).click();
	}
	
	public void userLogIn(int row) {		
		driver.findElement(By.xpath(xPaths.get(USERNAME))).sendKeys(desiredData(row, 1, "src/test/resources/users.xlsx"));
		driver.findElement(By.xpath(xPaths.get(PASSWORD))).sendKeys(desiredData(row, 3, "src/test/resources/users.xlsx"));
		driver.findElement(By.xpath(xPaths.get(LOGINBUTTON))).click();
	}
	

}

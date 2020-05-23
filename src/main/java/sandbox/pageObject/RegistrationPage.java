package sandbox.pageObject;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
	
	WebDriver driver;
	Map<String, String> xPaths;
	
	
	public static String ACCOUNT = "ACCOUNT"; 
	public static String USERMANAGEMENT = "USERMANAGEMENT"; 
	public static String CREATEUSERNAME = "CREATEUSERNAME"; 
	public static String TYPEEMAIL = "TYPEEMAIL"; 
	public static String TYPEUSERNAME = "TYPEUSERNAME"; 
	public static String TYPEPASSWORD = "TYPEPASSWORD"; 
	public static String TYPECONFIRMPASSWORD = "TYPECONFIRMPASSWORD";
	public static String CREATEUSERNAMEBUTTON = "CREATEUSERNAMEBUTTON"; 
	public static String UPDATEUSERTEXT = "UPDATEUSERTEXT"; 
	public static String INVALIDEMAIL = "INVALIDEMAIL";
	public static String INVALIDUSERNAME = "INVALIDUSERNAME";
	public static String INVALIDPASSWORD = "INVALIDPASSWORD"; 
	public static String PASSWORDNOMATCH = "PASSWORDNOMATCH";
	
	public RegistrationPage(WebDriver driver, Map<String, String> xPaths) {
		this.driver = driver;
		this.xPaths = xPaths;
	}
	
	public void registerUser (String email, String username, String password, String pass) {
		driver.findElement(By.xpath(xPaths.get(ACCOUNT))).click();
		driver.findElement(By.xpath(xPaths.get(USERMANAGEMENT))).click();
		driver.findElement(By.xpath(xPaths.get(CREATEUSERNAME))).click();
		driver.findElement(By.xpath(xPaths.get(TYPEEMAIL))).sendKeys(email);
		driver.findElement(By.xpath(xPaths.get(TYPEUSERNAME))).sendKeys(username);
		driver.findElement(By.xpath(xPaths.get(TYPEPASSWORD))).sendKeys(password);
		driver.findElement(By.xpath(xPaths.get(TYPECONFIRMPASSWORD))).sendKeys(pass);
		driver.findElement(By.xpath(xPaths.get(CREATEUSERNAMEBUTTON))).click();
	}
	
	public String updateUserText() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPaths.get(UPDATEUSERTEXT))));
		return driver.findElement(By.xpath(xPaths.get(UPDATEUSERTEXT))).getText();
	}
	
	public String invalidEmail() {
		return driver.findElement(By.xpath(xPaths.get(INVALIDEMAIL))).getText();
	}
	
	public String invalidUsername () {
		return driver.findElement(By.xpath(xPaths.get(INVALIDUSERNAME))).getText();
	}
	
	public String invalidPassword () {
		return driver.findElement(By.xpath(xPaths.get(INVALIDPASSWORD))).getText();
	}
	
	public String noPasswordMatch () {
		return driver.findElement(By.xpath(xPaths.get(PASSWORDNOMATCH))).getText();
	}
	
}

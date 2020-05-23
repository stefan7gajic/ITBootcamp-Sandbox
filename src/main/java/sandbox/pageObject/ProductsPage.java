package sandbox.pageObject;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sandbox.readingFromXlsxFile.ReadingFromXlsxFile;

public class ProductsPage extends ReadingFromXlsxFile {
	
	public static String PRODUCTS = "PRODUCTS";
	public static String ADDNEWPRODUCTSBUTTON = "ADDNEWPRODUCTSBUTTON";
	public static String TYPEPRODUCTNAME = "TYPEPRODUCTNAME";
	public static String TYPEPRODUCTPRICE = "TYPEPRODUCTPRICE";
	public static String SAVECHANGESBUTTON = "SAVECHANGESBUTTON";
	public static String SUCCESSFULUPDATEMESSAGE = "SUCCESSFULUPDATEMESSAGE";
	public static String EDITPRODUCTS = "EDITPRODUCTS";
	public static String SAVEPRODUCTCHANGE = "SAVEPRODUCTCHANGE";
	public static String SAVEDMESSAGE = "SAVEDMESSAGE";
	
	WebDriver driver;
	Map<String, String> xPaths;
	
	public ProductsPage(WebDriver driver, Map<String, String> xPaths) {
		this.driver = driver;
		this.xPaths = xPaths;
	}
	
	public void addNewProduct(int row) {
		driver.findElement(By.xpath(xPaths.get(PRODUCTS))).click();
		driver.findElement(By.xpath(xPaths.get(ADDNEWPRODUCTSBUTTON))).click();
		driver.findElement(By.xpath(xPaths.get(TYPEPRODUCTNAME))).sendKeys(desiredData(row, 0, "src/test/resources/products.xlsx"));
		driver.findElement(By.xpath(xPaths.get(TYPEPRODUCTPRICE))).sendKeys(desiredData(row, 1, "src/test/resources/products.xlsx"));
		driver.findElement(By.xpath(xPaths.get(SAVECHANGESBUTTON))).click();
		
		
	}
	
	public String successfullyAddedProductMessage() {
		return driver.findElement(By.xpath(xPaths.get(SUCCESSFULUPDATEMESSAGE))).getText();
	}
	
	public void clickProductsCard() {
		driver.findElement(By.xpath(xPaths.get(PRODUCTS))).click();
	}
	
	public void clickEditProducts() {
		driver.findElement(By.xpath(xPaths.get(EDITPRODUCTS))).click();
	}
	
	public void increasePrice (Double num) {
		List<WebElement> prods = driver.findElements(By.xpath("//*[contains(@name, 'price')]"));
		Iterator<WebElement>itr = prods.iterator();
		while(itr.hasNext()) {
			WebElement element = itr.next();
			String value = element.getAttribute("value");
			double increasedValue = Double.parseDouble(value)+num;
			element.clear();
			element.sendKeys(String.valueOf(increasedValue));
		}
	}
	
	public void clickSaveProductChange() {
		driver.findElement(By.xpath(xPaths.get(SAVEPRODUCTCHANGE))).click();
	}
	
	public String successfullySavedProductChangesMessage() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPaths.get(SAVEDMESSAGE))));
		return driver.findElement(By.xpath(xPaths.get(SAVEDMESSAGE))).getText();
	}


}

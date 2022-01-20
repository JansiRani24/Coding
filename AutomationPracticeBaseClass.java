package www.automationpractice.pom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutomationPracticeBaseClass {
	
	public static WebDriver driver;

	// Browser Launch

	public static WebDriver getBrowser(String browserLanuch) {

		try {
			if (browserLanuch.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"D:\\Jansi\\Green technology\\Java_programs\\BaseClass\\Driver\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserLanuch.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver",
						"D:\\Jansi\\Green technology\\Java_programs\\BaseClass\\Driver\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			} else {
				System.out.println("Invaid Browser Name");
			}

			driver.manage().window().maximize();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return driver;

	}

	// GET URL

	public static void getUrl(String urlname) {
		try {
			driver.get(urlname);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public static WebElement getfindElement(String attributeName, String attributeValue) {
		try {
			switch (attributeName) {
			case "id":
				return driver.findElement(By.id(attributeValue));
				//			break;
			case "name":
				return driver.findElement(By.name(attributeValue));
				//			break;
			case "xpath":
				return driver.findElement(By.xpath(attributeValue));
				//			break;
			default:
				break;
			}
			return null;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	// mouse action

	public static WebElement getmouseOver(WebElement element) {

		try {
			Actions ac = new Actions(driver);
			ac.moveToElement(element).perform();

			return element;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	// send keys

	public static void toSendKeys(WebElement element, String key) {
		try {
			element.sendKeys(key);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//	aravind send keys

	public static void type(WebElement element ,String type , Keys keys) {
		element.sendKeys(type , keys);
	}

	// to close the browser

	public static void toCloseAndQuit(String function) {
		try {
			switch (function) {
			case "close":
				driver.close();
				break;
			case "quit":
				driver.quit();
				break;
			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//navigation

	public static void navigations(String function) {
		try {
			switch (function) {
			case "backward":
				driver.navigate().back();
				break;
			case "forward":
				driver.navigate().forward();
				break;
			case "refresh":
				driver.navigate().refresh();
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// taking Screenshot
	public static void captureScreen(String path, int k) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File destination = new File(path+"\\image"+k+".png");
			FileUtils.copyFile(source, destination);
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	// waits-implicit

	public static void implicitWaits() {
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}

	// waits-explicit

	public static  void explicitWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeSelected(element));
	}

	//windows Handling
	public static void switchtowindow(int i) {
		Set<String> windowHandles = driver.getWindowHandles();
		ArrayList<String> li = new ArrayList<String>(windowHandles);
		driver.switchTo().window(li.get(i));
	}

	//alerts

	public static void getalert(String type) {
		try {
			switch (type) {
			case "accept":
				Alert alert = driver.switchTo().alert();
				alert.accept();
				break;
			case "dismiss":
				Alert alert1 = driver.switchTo().alert();
				alert1.dismiss();
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//	//Scroll up down by locator 
	//	public static void scrollingUpDownByID(WebElement element, String s ) {
	//
	//		driver.findElement(By.id(s));
	//		JavascriptExecutor js = (JavascriptExecutor) driver;
	//		js.executeScript("arguments[0].scrollIntoView();",element);
	//	}
	//
	//	//Scroll up down by locator 
	//	public static void scrollingUpDownByName(WebElement element, String s ) {
	//
	//		driver.findElement(By.name(s));
	//		JavascriptExecutor js = (JavascriptExecutor) driver;
	//		js.executeScript("arguments[0].scrollIntoView();",element);
	//	}

	//Scroll up down by locator 
	public static void scrollingUpDown(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();",element);
	}

	// scroll up and down normally

	public static void scroll() throws InterruptedException {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		js.executeScript("window.scrollTo(0,0)");

	}

	// to click

	public static void toClick(WebElement element) {
		try {
			element.click();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//Drop-Down by visible text

	public static void DDbyVisibleText(WebElement element, String visibleText ) {

		Select s = new Select(element);
		s.selectByVisibleText(visibleText);
	}

	//Drop-Down by index

	public static void DDbyIndex(WebElement element,int i ) {

		Select s = new Select(element);
		s.selectByIndex(i);
	}

	//Drop-Down by value

	public static void DDbyValue(WebElement element, String value ) {

		Select s = new Select(element);
		s.selectByValue(value);
	}

	//Aravind DD methods
	//	public static void selectbyvalue(WebElement element, String value) {
	//		new Select(element).selectByValue(value);
	//	}
	//	public static void selectbyindex(WebElement element ,int index) {
	//		new Select(element).selectByIndex(index);
	//	}
	//	public static void selectbytext(WebElement element ,String text) {
	//		new Select(element).selectByVisibleText(text);
	//	}

	//frames by locators

	public static WebElement framesByLocators(WebElement element) {
				driver.switchTo().frame(element);
				return element;
	}
	
	
	//frames by value
	public static void frame(int value) {
		driver.switchTo().frame(0);
	}

	//default frame or parent frame

	public static void defaultFrame() {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Context Click

	public static void rightClick(WebElement element) {
				Actions ac = new Actions (driver);
				ac.contextClick(element).perform();
		
	}

}

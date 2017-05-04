package stremaing.control;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class WebSession {
	WebDriver driver;
	Actions action;
	String text;
	public WebSession(){
		start();
	}
	public void start(){
		System.setProperty("webdriver.chrome.driver", "./chromedriver");
		driver = new ChromeDriver();
		action = new Actions(driver);
		getHome();
	}
	public void quit(){
		driver.quit();
	}
	public void getHome(){
		navTo("https://www.google.com/");
	}
	public void navTo(String address){
		try{
			driver.get(address);
		}catch(WebDriverException e){
			System.out.println("There's something wrong with that url. Please try again.");
		}
		driver.get(address);
	}
	public void inputText(String text){
		driver.switchTo().activeElement().sendKeys(text);
		//driver.findElement(By.xpath(xpath)).sendKeys(text);
	}
	public void click(){
		String xpath = createXpath();
		int i = driver.findElements(By.xpath(xpath)).size();
		WebElement e = driver.findElements(By.xpath(xpath)).get(0);
		
		action.moveToElement(e).click().perform();

	}

	public void scrollDown(){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
	}
	public void scrollUp(){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,-250)", "");
	}
	public void rightClick(){
		action.contextClick(driver.findElement(By.xpath(createXpath()))).perform();
	}
	public void mouseOver(){
		action.moveToElement(driver.findElement(By.xpath(createXpath()))).perform();
	}
	public void doubleClick(){
		action.doubleClick(driver.findElement(By.xpath(createXpath()))).perform();
	}
	public void setText(String txt){
		text = txt;
	}
	public void pressEnter(){
		action.sendKeys(Keys.ENTER).perform();
	}
	public void pressTab(){
		action.sendKeys(Keys.TAB).perform();
	}
	public void pressDown(){
		action.sendKeys(Keys.ARROW_DOWN).perform();
	}
	public void pressUp(){
		action.sendKeys(Keys.ARROW_UP).perform();
	}
	public void pageUp(){
		action.sendKeys(Keys.PAGE_UP).perform();
	}
	public void pageDown(){
		action.sendKeys(Keys.PAGE_DOWN).perform();
	}
	public void goBack(){
		driver.navigate().back();
	}
	public String createXpath(){
		String xpath="";

		if(driver.findElements(By.xpath("//*[(text())[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), \""+text+"\")]]")).size()>0){
			xpath = "[(text())[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), \""+text+"\")]]";
		}
		else if(driver.findElements(By.xpath("//*[(@title)[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), \""+text+"\")]]")).size()>0){
			xpath = "[(@title)[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), \""+text+"\")]]";
		}
		else if(driver.findElements(By.xpath("//*[(@value)[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), \""+text+"\")]]")).size()>0){
			xpath = "[(@value)[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), \""+text+"\")]]";
		}
		else{
			xpath = "[(@id|@aria-label|@class|@href)[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), \""+text+"\")]]";

		}
		if(driver.findElements(By.xpath("//a"+xpath)).size()>0){
			xpath = "//a"+xpath;
		}
		else if(driver.findElements(By.xpath("//span"+xpath)).size()>0){
			xpath = "//span"+xpath;
		}
		else if(driver.findElements(By.xpath("//input"+xpath)).size()>0){
			xpath = "//input"+xpath;
		}
		else if(driver.findElements(By.xpath("//div"+xpath)).size()>0){
			xpath = "//div"+xpath;
		}
		else{
			xpath = "//*"+xpath;
		}
		return xpath;
	}



	  


}

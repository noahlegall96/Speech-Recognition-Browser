import java.io.File;
import java.io.FileInputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class WebSession2 {
	WebDriver driver;
	Actions action;
	String text;
	public WebSession2(){
		start();
	}
	public void start(){
		System.setProperty("webdriver.chrome.driver", "C:/Users/nrjoseph/Documents/COMP 580/chromedriver.exe");
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
		driver.get(address);
	}
	public void inputText(String xpath,String text){
		driver.findElement(By.xpath(xpath)).sendKeys(text);
	}
	public void click(){
		//driver.findElement(By.xpath(xpath)).click();
		driver.findElement(By.xpath("//*[contains(text(),'"+text+"')]")).sendKeys(Keys.RETURN);
	}
//	public void click(String xpath){
//		//driver.findElement(By.xpath(xpath)).click();
//		driver.findElement(By.xpath("//*[contains(text(),'"+text+"')]")).sendKeys(Keys.RETURN);
//	}
	public void scrollDown(){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
	}
	public void scrollUp(){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,-250)", "");
	}
	public void rightClick(String xpath){
		action.contextClick(driver.findElement(By.xpath(xpath))).perform();
	}
	public void mouseOver(String xpath){
		action.moveToElement(driver.findElement(By.xpath(xpath))).perform();
	}
	public void doubleClick(String xpath){
		action.doubleClick(driver.findElement(By.xpath(xpath))).perform();
	}
	public void setText(String txt){
		text = txt;
	}


	  


}

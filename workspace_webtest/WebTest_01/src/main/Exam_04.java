package main;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Exam_04 {
	public static void main(String[] args) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--blink-settings=imagesEnabled=false");
		WebDriver driver = new ChromeDriver(options);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		driver.get("http://127.0.0.1:5500/exam01.html");
		js.executeScript("pop()");
		
		driver.switchTo().alert().accept();
	}
}

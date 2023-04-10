package main;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Exam_03 {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		driver.manage().window().setSize(new Dimension(1300, 600));
		driver.get("https://www.interpark.com/");
		
		String parentHandle = driver.getWindowHandle();
		
		driver.findElement(By.linkText("·Î±×ÀÎ")).click();
		Set<String> handles = driver.getWindowHandles();
		System.out.println(handles);
		for(String curHandle : handles) {
			if(!curHandle.equals(parentHandle)) {
				driver.switchTo().window(curHandle);
				break;
			}
		}
		driver.findElement(By.id("userId")).sendKeys("Tester");
		driver.findElement(By.id("userPwd")).sendKeys("12345");
//		driver.findElement(By.id("btn_login")).click();
	}
}

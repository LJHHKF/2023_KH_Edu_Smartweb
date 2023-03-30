package main;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Exam_02 {
	public static void main(String[] args) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--blink-settings=imagesEnabled=false");
		WebDriver driver = new ChromeDriver(options);
		//2번째 인자는 '10초 기다리다가 넘으면 에러내라'는 의미
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		driver.get("https://nid.naver.com/nidlogin.login?mode=form&url=https%3A%2F%2Fwww.naver.com");
//		WebElement inputId = driver.findElement(By.id("id"));
//		WebElement inputPw = driver.findElement(By.id("pw"));
//		inputId.sendKeys("tester");
//		inputPw.sendKeys("testPw");
		
		js.executeScript("document.getElementById('id').value = arguments[0]", Statics.NAVER_ID);
		js.executeScript("document.getElementById('pw').value = arguments[0]", Statics.NAVER_PW);
		driver.findElement(By.id("log.login")).click();
		driver.get("https://mail.naver.com/v2/folders/0/all");
		
//		List<WebElement> btnWriteToMe = null;
//		while(true) {
//			btnWriteToMe = driver.findElements(By.className("button_write_to_me"));
//			if(btnWriteToMe.size() > 0) {
//				System.out.println(btnWriteToMe.size());
//				break;
//			}
//		}
//		btnWriteToMe.get(0).click();
		
//		wait.until(ExpectedConditions.numberOfElementsToBe(By.className("button_write_to_me"), 1));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("button_write_to_me")));
		List<WebElement> btnWriteToMe = driver.findElements(By.className("button_write_to_me"));
		btnWriteToMe.get(0).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("subject_title")));
		driver.findElement(By.id("subject_title")).sendKeys("Selenium Test");
//		driver.switchTo().frame(driver.findElements(By.cssSelector(".editor_body>iframe")).get(0));
		driver.switchTo().frame(driver.findElements(By.className("editor_body")).get(0).findElements(By.tagName("iframe")).get(0));
		WebElement editorBody = driver.findElements(By.className("workseditor-content")).get(0);
		editorBody.sendKeys("test success");
		
		driver.switchTo().defaultContent();
		driver.findElements(By.className("button_write_task")).get(0).click();
		
//		driver.switchTo().frame("gfp_iframe_uuid"); //iframe으로 포커스가 분산되어 들어가지 못하니 강제로 포커스 이동.
	}
}

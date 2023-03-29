package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Exam_01 {
	public static void main(String[] args) {
		// UI 테스트
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.naver.com");
		
		WebElement inputSearch = driver.findElement(By.id("query"));
		inputSearch.sendKeys("KH정보교육원");
		WebElement btnSearch = driver.findElement(By.id("search_btn"));
		btnSearch.click();
	}
}

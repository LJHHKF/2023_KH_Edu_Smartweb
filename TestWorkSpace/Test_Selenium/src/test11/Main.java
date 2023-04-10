package test11;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
//		driver.get("https://www.iei.or.kr/main/main.kh");
//		WebElement loginBtn = driver.findElement(By.id("head_top_nav")).findElements(By.className("head_top_right")).get(0).findElements(By.tagName("a")).get(0);
//		loginBtn.click();
		
		driver.get("https://www.iei.or.kr/login/login.kh");

		js.executeScript("document.getElementById('id').value = arguments[0]", Statics.kh_id);
		js.executeScript("document.getElementById('password').value = arguments[0]", Statics.kh_pw);
		WebElement loginConfirm = driver.findElement(By.id("login")).findElements(By.tagName("a")).get(0);
		loginConfirm.click();
		wait.until(ExpectedConditions.urlMatches("https://www.iei.or.kr/login/mypage.kh"));
		
		//3. 우리반 게시판으로 이동
		js.executeScript("location.href = '/login/currBoard.kh'");
		wait.until(ExpectedConditions.urlMatches("https://www.iei.or.kr/login/currBoard.kh"));
		
		//4.글 쓰기 버튼을 눌러 글 작성 화면으로 이동
		js.executeScript("fnWriteForm()");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("title")));
		
		//5.글 작성 화면으로 이동되었다면, 제목 부분에 다음 내용을 작성. "시험문제 작성중입니다. 제목 - 홍길동"
		driver.findElement(By.id("title")).sendKeys("시험문제 작성중입니다. 제목 - 이주화");
		
		//6. 글 내용을 작성하는 테스트 코드를 작성. 정확히는 프레임 변경만 작성.
		driver.switchTo().frame(driver.findElement(By.id("tx_canvas_wysiwyg")));
		
		//7. 변경된 프레임에서 내용 작성 부분에 다음과 같이 작성.
		//시험문제 답안 작성중입니다. 내용 - frame 처리
		driver.findElements(By.className("tx-content-container")).get(0).sendKeys("시험문제 답안 작성중입니다. 내용 - frame 처리");
		
		//8.글 작성 완료 버튼을 눌러 글 작성을 완료하세요. 마찬가지로 프레임 변경 얘기임. 패런트 프레임.
		driver.switchTo().defaultContent();
		
		//9.프레임 변경이 완료되었다면 작성 완료 버튼을 눌러 완료. 버튼 자바스크립트.
		js.executeScript("fnRegister()");
		
		//10. 작성 완료되었다는 확인창이 뜨면 확인 버튼을 누르고, 작성된 글 확인.
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		driver.close();
	}
}

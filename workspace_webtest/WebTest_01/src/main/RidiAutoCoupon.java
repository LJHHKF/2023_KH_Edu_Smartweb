package main;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RidiAutoCoupon {
	//리디북스 십오야(14일 3시~21일 자정, 10% 쿠폰 및 포인트 제공. 기간 내 일정금액 구매 시 추가 포인트 제공)
	//& 리디북스 화요쿠폰
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int menu = 0;
		while(true) {
			try {
				System.out.println("이하 중 원하는 동작을 선택하여 메뉴 번호를 입력해주세요.");
				System.out.println("1. 십오야 쿠폰 발급");
				System.out.println("2. 화요 쿠폰 발급");
				System.out.println("3. 쿠폰 두개 다 발급");
				System.out.println("0. 프로그램 종료");
				System.out.print(">");
				menu = Integer.parseInt(sc.nextLine());
				if(menu >= 0 && menu <= 3) {
					break;
				}else {
					System.out.println("메뉴를 다시 확인해주세요.");
				}
			}catch(Exception e) {
				System.out.println("숫자를 입력해주세요.");
			}
		}
		if(menu==0) {
			System.out.println("프로그램을 종료합니다.");
			System.exit(0);
		}
		
		RidiAutoCoupon self = new RidiAutoCoupon();
		
		//옵션에서 블링크 세팅(이미지 안 불러오기 세팅) 쓰면 안 됨.
		//버튼이 아닌 '이미지 위 좌표값'으로 동작하다보니, 이미지가 나와야 동작함.
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		WebDriver driver = new ChromeDriver(options);
//		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		if(menu == 1) {
			self.coupon_15night(driver);
			self.login(driver, wait);
			self.touchAlert(driver, wait);
		}else if(menu == 2) {
			self.coupon_weeklyTusday(driver);
			self.login(driver, wait);
			self.touchAlert(driver, wait);
		}else if(menu == 3) {
			self.coupon_15night(driver);
			self.login(driver, wait);
			self.touchAlert(driver, wait);
			self.coupon_weeklyTusday(driver);
			self.touchAlert(driver, wait);
		}
	}
	private void login(WebDriver driver, WebDriverWait wait) {
		//로그인 입력. js 방식은 만들어 보긴 했고, 값이 들어가긴 하나, 정작 돌려보면 인식 못함. 막아둔 듯.
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("fig-w58liu")));
		driver.findElement(By.className("fig-w58liu")).sendKeys(Statics.RIDI_ID);
		driver.findElement(By.className("fig-7he7ta")).sendKeys(Statics.RIDI_PW);
		List<WebElement> buttons = driver.findElements(By.tagName("button"));
		for(WebElement curBtn : buttons) {
			if(curBtn.getDomAttribute("type").equals("submit")) {
				curBtn.click();
				break;
			}
		}
	}
	private void touchAlert(WebDriver driver, WebDriverWait wait) {
		//쿠폰 발급 성공 또는 실패(이미 발급 등) 모두 alert 창 방식으로 안내가 나옴.
		//성공시에도 나왔는지는 테스트는 못 해봤고 기억 기반.
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}
	private void coupon_15night(WebDriver driver) {
		//십오야 페이지 접속
		driver.get("https://ridibooks.com/event/3799");
		//십오야 이미지 영역 클릭. 로그인 창으로 넘어갈 것임.
		WebElement imgMap = driver.findElement(By.name("imagemap1"));
		List<WebElement> maps = imgMap.findElements(By.tagName("area"));
		for(WebElement curArea : maps) {
			if(curArea.getDomAttribute("alt").equals("랜덤 리워드")) {
				curArea.click();
				break;
			}
		}
	}
	private void coupon_weeklyTusday(WebDriver driver) {
		//화요 쿠폰 페이지 접속
		driver.get("https://ridibooks.com/event/19350");
		//화요 쿠폰 이미지 영역 클릭.
		WebElement imgMap = driver.findElement(By.name("imagemap1"));
		//윗 map 안에 area 태그 1개 들어가있음.
		imgMap.findElement(By.tagName("area")).click();
	}
}

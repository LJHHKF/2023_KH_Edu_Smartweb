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
	//����Ͻ� �ʿ���(14�� 3��~21�� ����, 10% ���� �� ����Ʈ ����. �Ⱓ �� �����ݾ� ���� �� �߰� ����Ʈ ����)
	//& ����Ͻ� ȭ������
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int menu = 0;
		while(true) {
			try {
				System.out.println("���� �� ���ϴ� ������ �����Ͽ� �޴� ��ȣ�� �Է����ּ���.");
				System.out.println("1. �ʿ��� ���� �߱�");
				System.out.println("2. ȭ�� ���� �߱�");
				System.out.println("3. ���� �ΰ� �� �߱�");
				System.out.println("0. ���α׷� ����");
				System.out.print(">");
				menu = Integer.parseInt(sc.nextLine());
				if(menu >= 0 && menu <= 3) {
					break;
				}else {
					System.out.println("�޴��� �ٽ� Ȯ�����ּ���.");
				}
			}catch(Exception e) {
				System.out.println("���ڸ� �Է����ּ���.");
			}
		}
		if(menu==0) {
			System.out.println("���α׷��� �����մϴ�.");
			System.exit(0);
		}
		
		RidiAutoCoupon self = new RidiAutoCoupon();
		
		//�ɼǿ��� ��ũ ����(�̹��� �� �ҷ����� ����) ���� �� ��.
		//��ư�� �ƴ� '�̹��� �� ��ǥ��'���� �����ϴٺ���, �̹����� ���;� ������.
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
		//�α��� �Է�. js ����� ����� ���� �߰�, ���� ���� �ϳ�, ���� �������� �ν� ����. ���Ƶ� ��.
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
		//���� �߱� ���� �Ǵ� ����(�̹� �߱� ��) ��� alert â ������� �ȳ��� ����.
		//�����ÿ��� ���Դ����� �׽�Ʈ�� �� �غð� ��� ���.
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}
	private void coupon_15night(WebDriver driver) {
		//�ʿ��� ������ ����
		driver.get("https://ridibooks.com/event/3799");
		//�ʿ��� �̹��� ���� Ŭ��. �α��� â���� �Ѿ ����.
		WebElement imgMap = driver.findElement(By.name("imagemap1"));
		List<WebElement> maps = imgMap.findElements(By.tagName("area"));
		for(WebElement curArea : maps) {
			if(curArea.getDomAttribute("alt").equals("���� ������")) {
				curArea.click();
				break;
			}
		}
	}
	private void coupon_weeklyTusday(WebDriver driver) {
		//ȭ�� ���� ������ ����
		driver.get("https://ridibooks.com/event/19350");
		//ȭ�� ���� �̹��� ���� Ŭ��.
		WebElement imgMap = driver.findElement(By.name("imagemap1"));
		//�� map �ȿ� area �±� 1�� ������.
		imgMap.findElement(By.tagName("area")).click();
	}
}

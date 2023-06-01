package kh.spring.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestScheduler {
	
	@Scheduled(fixedRate = 5000)
	public void test() {
		System.out.println("스케쥴러 동작 확인");
	}
}

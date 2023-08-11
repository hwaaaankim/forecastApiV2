package com.dev.ForecastApiTestJarV2.schedule;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableAsync
@Component
public class SchedulingController {

	// 체인 퀘스트 등록 스케줄링 Method 
	@Async
	@Scheduled(cron = "0 5 * * * *")
	public void testScheduling() throws InterruptedException{
		System.out.println("123");
	}
	
}

package com.dev.ForecastApiTestJarV2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableScheduling
@EnableCaching
public class ForecastApiTestJarV2Application {

	public static void main(String[] args) {
		SpringApplication.run(ForecastApiTestJarV2Application.class, args);
	}

}

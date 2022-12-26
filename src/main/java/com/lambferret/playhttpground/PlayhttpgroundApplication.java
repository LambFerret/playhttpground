package com.lambferret.playhttpground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories()
public class PlayhttpgroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayhttpgroundApplication.class, args);
	}

}

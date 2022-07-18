package com.kolokolnin.exchangeParse;

import com.kolokolnin.exchangeParse.job.ParseTask;
import com.kolokolnin.exchangeParse.service.ExchangeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
@Slf4j
@AllArgsConstructor
@SpringBootApplication
public class ExchangeParseApplication implements ApplicationRunner {

	private final ParseTask parseTask;
	private final ExchangeService exchangeService;

	public static void main(String[] args) {
		new SpringApplicationBuilder()
				.sources(ExchangeParseApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.run(args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		parseTask.parseExchange();
		log.info("-----------------");
		exchangeService.findAll()
				.forEach(exchange -> log.info("fetching data from database: {}", exchange));
	}
}

package com.kolokolnin.exchangeParse.job;

import com.kolokolnin.exchangeParse.model.Exchange;
import com.kolokolnin.exchangeParse.service.ExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Slf4j
public class ParseTask {

    @Autowired
    private ExchangeService exchangeService;

    @Value("${banki.currency.url}")
    private String currencyUrl;

    @Scheduled(fixedDelay = 10000L)
    public void parseExchange() throws IOException {
        log.info("scheduler is working now to parse exchange");

    getDOMChildren().stream()
            .peek(child -> log.info("fetching the result from URL to parse: {}", child))
            .map(this::buildExchange)
            .peek(exchange -> log.info("saving the result: {}", exchange))
            .forEach(exchangeService::save);

        log.info("scheduler has been done the process of parsing");

    }
    private Exchange buildExchange(Element element){
        return Exchange.builder()
                .charName(element.child(0).text())
                .nominal(Integer.parseInt(element.child(1).text()))
                .name(element.child(2).text())
                .value(Double.parseDouble(element.child(3).text()))
                .changed(element.child(4).text())
                .build();
    }

    private Elements getDOMChildren() throws IOException {
        return  buildConnection().get().getElementsByTag("tbody");
    }

    private Connection buildConnection(){
        return Jsoup.connect(currencyUrl)
                .userAgent("searchEngineBot/0.1")
                .referrer("http://www.google.com")
                .ignoreHttpErrors(true)
                .ignoreContentType(true)
                .timeout(5000);
    }
}

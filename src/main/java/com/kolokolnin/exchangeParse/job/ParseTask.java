package com.kolokolnin.exchangeParse.job;

import com.kolokolnin.exchangeParse.model.Exchange;
import com.kolokolnin.exchangeParse.service.ExchangeService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ParseTask implements Perser{

    @Autowired
    Exchange exchange;
    List<Exchange> exchangeList = new ArrayList<>();

    String url = "https://www.banki.ru/products/currency/cb/";

    @Scheduled(fixedDelay = 10000L)
    public void parseNewExchange() throws IOException {

        Connection document = Jsoup.connect(url).userAgent("searchEngineBot/0.1").referrer("http://www.google.com")
                .ignoreHttpErrors(true).ignoreContentType(true).timeout(5000);
        Elements tabels = document.get().getElementsByTag("tbody");
        Element our_table = tabels.get(0);
        Elements elements_from_tabele = our_table.children();


        for (int i = 0; i < our_table.childrenSize(); i++) {
            exchange = new Exchange();
            exchange.setId(i);
            exchange.setCharName(our_table.children().get(i).child(0).text());
            exchange.setNominal(Integer.parseInt(our_table.children().get(i).child(1).text()));
            exchange.setName(our_table.children().get(i).child(2).text());
            exchange.setValue(Double.parseDouble(our_table.children().get(i).child(3).text()));
            exchange.setChanged(our_table.children().get(i).child(4).text());
            exchangeList.add(exchange);
        }

    }
}

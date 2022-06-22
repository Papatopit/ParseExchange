package com.kolokolnin.exchangeParse;

import com.kolokolnin.exchangeParse.model.Exchange;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        String data_1;
        String data_2;
        String data_3;
        String data_4;
        String data_5;

        Exchange exchange;
        List<Exchange> exchangeList = new ArrayList<>();


        String url = "https://www.banki.ru/products/currency/cb/";

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

        System.out.println(exchangeList);



    }
}

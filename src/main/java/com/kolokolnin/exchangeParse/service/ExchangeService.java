package com.kolokolnin.exchangeParse.service;

import com.kolokolnin.exchangeParse.model.Exchange;
import com.kolokolnin.exchangeParse.repository.ExchangeRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExchangeService {
    public void save(Exchange exchange);
    public boolean isExist(String exchangeName);
    public List<Exchange> getAllExchange();

}

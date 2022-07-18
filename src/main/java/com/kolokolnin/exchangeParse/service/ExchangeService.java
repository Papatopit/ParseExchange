package com.kolokolnin.exchangeParse.service;

import com.kolokolnin.exchangeParse.model.Exchange;
import com.kolokolnin.exchangeParse.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExchangeService {
    @Autowired
    ExchangeRepository exchangeRepository;

    public Optional<Exchange> findById(Long id) {
        return exchangeRepository.findById(id);
    }


    public void save(Exchange exchange) {
        exchangeRepository.save(exchange);
    }

    public List<Exchange> findAll() {
        return exchangeRepository.findAll();
    }

    public void saveAll(List<Exchange> exchangeList) {
        exchangeRepository.saveAll(exchangeList);
    }
}

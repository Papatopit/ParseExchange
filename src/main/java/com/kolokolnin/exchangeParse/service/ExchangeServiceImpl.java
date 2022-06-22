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
public class ExchangeServiceImpl implements ExchangeService{
    @Autowired
    ExchangeRepository exchangeRepository;

    public Optional<Exchange> getById(Long id){
        return exchangeRepository.findById(id);
    }

    @Override
    public void save(Exchange exchange) {
        exchangeRepository.save(exchange);
    }

    @Override
    public boolean isExist(String exchangeName) {
        List<Exchange> exchanges = exchangeRepository.findAll();

        for (Exchange ex : exchanges){
            if (ex.getName().equals(exchangeName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Exchange> getAllExchange() {
       return exchangeRepository.findAll() ;
    }

    public void saveAll(List<Exchange> exchangeList){
        exchangeRepository.saveAll(exchangeList);
    }
}

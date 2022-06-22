package com.kolokolnin.exchangeParse.repository;

import com.kolokolnin.exchangeParse.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange,Long> {

}

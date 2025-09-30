package com.example.NextgenBnp.repository;

import com.example.NextgenBnp.model.StockAnalyser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StockRepository extends MongoRepository<StockAnalyser, String> {
    Optional<StockAnalyser> findByStockSymbol(String stockSymbol);
}


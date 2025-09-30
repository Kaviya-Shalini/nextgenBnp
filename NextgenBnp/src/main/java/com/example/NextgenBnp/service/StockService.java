package com.example.NextgenBnp.service;
import com.example.NextgenBnp.model.StockAnalyser;
import com.example.NextgenBnp.repository.StockRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    // Get all stock symbols
    public List<String> getAllStockSymbols() {
        return stockRepository.findAll()
                .stream()
                .map(StockAnalyser::getStockSymbol)
                .toList();
    }

    // Get stock parameters by symbol
    public Map<String, Object> getStockParameters(String symbol) {
        return stockRepository.findByStockSymbol(symbol)
                .map(StockAnalyser::getParameters)
                .orElse(Collections.emptyMap());
    }
    @PostConstruct
    public void testRepo() {
        System.out.println("Stock count: " + stockRepository.count());
        stockRepository.findAll().forEach(s ->
                System.out.println("Loaded stock: " + s.getStockSymbol())
        );
    }


    // Evaluate stock
    public Map<String, Object> evaluateStock(String symbol, Map<String, Object> parameters) {
        Map<String, Object> response = new HashMap<>();

        // Feedback for each parameter
        Map<String, String> feedback = new HashMap<>();

        // Simple example evaluation logic
        double pe = Double.parseDouble(parameters.get("priceEarningsRatio").toString());
        feedback.put("priceEarningsRatio", pe < 20 ? "Good valuation" : "High valuation");

        double roe = Double.parseDouble(parameters.get("returnOnEquity").toString());
        feedback.put("returnOnEquity", roe > 0.15 ? "Strong profitability" : "Weak profitability");

        double debtToEquity = Double.parseDouble(parameters.get("debtToEquityRatio").toString());
        feedback.put("debtToEquityRatio", debtToEquity < 1 ? "Healthy debt level" : "High debt risk");

        double dividend = Double.parseDouble(parameters.get("dividendYield").toString());
        feedback.put("dividendYield", dividend > 0.5 ? "Decent dividends" : "Low dividends");

        // Final summary
        String summary = (roe > 0.15 && debtToEquity < 1 && pe < 25)
                ? "✅ This stock looks promising for investment."
                : "⚠️ This stock carries risks, evaluate carefully.";

        response.put("feedback", feedback);
        response.put("summary", summary);


        return response;
    }
}

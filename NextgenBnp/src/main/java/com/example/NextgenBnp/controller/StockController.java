package com.example.NextgenBnp.controller;
import com.example.NextgenBnp.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    // Get all stock symbols
    @GetMapping
    public List<String> getAllStocks() {
        return stockService.getAllStockSymbols();
    }

    // Get parameters for a stock
    @GetMapping("/{symbol}/parameters")
    public Map<String, Object> getStockParameters(@PathVariable String symbol) {
        return stockService.getStockParameters(symbol);
    }

    // Evaluate stock
    @PostMapping("/{symbol}/evaluate")
    public Map<String, Object> evaluateStock(@PathVariable String symbol,
                                             @RequestBody Map<String, Object> parameters) {
        return stockService.evaluateStock(symbol, parameters);
    }
}

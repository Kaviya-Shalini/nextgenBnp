package com.example.NextgenBnp.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Map;

@Document(collection = "stockanalyser")
public class StockAnalyser {

    @Id
    private String id;
    private String stockSymbol;
    private Map<String, Object> parameters; // flexible to hold different parameter types

    public StockAnalyser() {}

    public StockAnalyser(String stockSymbol, Map<String, Object> parameters) {
        this.stockSymbol = stockSymbol;
        this.parameters = parameters;
    }

    public String getId() {
        return id;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}


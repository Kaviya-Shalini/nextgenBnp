package com.example.NextgenBnp.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Map;

@Document(collection = "portfolio")
public class PortFolio {

    @Id
    private String id;
    private String clientId;
    private String currency;

    private List<Fund> funds;

    // getters and setters

    public static class Fund {
        private String fundCode;
        private double amount;
        private Map<String, Double> holdings; // Stock : %
        private Map<String, Double> sectors;  // Sector : %

        // getters and setters
        public String getFundCode() { return fundCode; }
        public void setFundCode(String fundCode) { this.fundCode = fundCode; }

        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }

        public Map<String, Double> getHoldings() { return holdings; }
        public void setHoldings(Map<String, Double> holdings) { this.holdings = holdings; }

        public Map<String, Double> getSectors() { return sectors; }
        public void setSectors(Map<String, Double> sectors) { this.sectors = sectors; }
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public List<Fund> getFunds() { return funds; }
    public void setFunds(List<Fund> funds) { this.funds = funds; }
}

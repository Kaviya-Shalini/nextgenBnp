package com.example.NextgenBnp.service;

import com.example.NextgenBnp.model.PortFolio;
import com.example.NextgenBnp.repository.PortFolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PortFolioService {
    @Autowired
    private PortFolioRepository repository;

    // Fetch all clients
    public List<String> getAllClients() {
        return repository.findAll()
                .stream()
                .map(PortFolio::getClientId)
                .collect(Collectors.toList());
    }

    // Evaluate client portfolio
    public Map<String, Object> evaluateClient(String clientId) {
        PortFolio portfolio = repository.findByClientId(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        double totalValue = portfolio.getFunds().stream()
                .mapToDouble(PortFolio.Fund::getAmount)
                .sum();

        // Aggregate sector diversification
        Map<String, Double> sectorDiversification = new HashMap<>();
        for (PortFolio.Fund fund : portfolio.getFunds()) {
            for (Map.Entry<String, Double> entry : fund.getSectors().entrySet()) {
                sectorDiversification.merge(entry.getKey(), entry.getValue() * fund.getAmount(), Double::sum);
            }
        }

        // Normalize sector diversification
        double finalTotal = totalValue;
        sectorDiversification.replaceAll((k, v) -> v / finalTotal);

        // Dummy performance values (replace with real data)
        Map<String, Double> performance = Map.of(
                "oneYearReturn", 12.5,
                "threeYearReturn", 30.2,
                "fiveYearReturn", 60.0
        );

        // Diversification metrics (example)
        Map<String, String> diversificationMetrics = Map.of(
                "sectorCount", String.valueOf(sectorDiversification.size()),
                "topSector", Collections.max(sectorDiversification.entrySet(), Map.Entry.comparingByValue()).getKey()
        );

        // Dummy diversification suggestions
        List<Map<String, String>> possibleDiversification = List.of(
                Map.of("sector", "Healthcare", "recommendation", "Add exposure to healthcare for stability"),
                Map.of("sector", "Energy", "recommendation", "Consider balancing energy sector")
        );

        // Build response
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> analysis = new HashMap<>();

        analysis.put("totalValue", totalValue);
        analysis.put("sectorDiversification", sectorDiversification);
        analysis.put("performance", performance);
        analysis.put("diversificationMetrics", diversificationMetrics);

        response.put("clientId", clientId);
        response.put("currency", portfolio.getCurrency());
        response.put("portfolioAnalysis", analysis);
        response.put("traderType", "Balanced Investor");
        response.put("possibleDiversification", possibleDiversification);
        response.put("summary", "This portfolio shows good sector balance but can improve with healthcare allocation.");

        return response;
    }
}

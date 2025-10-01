package com.example.NextgenBnp.controller;

import com.example.NextgenBnp.service.PortFolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clients")
// allow frontend React
public class PortFolioController {
    @Autowired
    private PortFolioService service;

    public PortFolioController(PortFolioService service) {
        this.service = service;
    }

    // Get all clients
    @GetMapping
    public List<String> getAllClients() {
        return service.getAllClients();
    }

    // Evaluate specific client
    @PostMapping("/{clientId}/evaluate")
    public Map<String, Object> evaluateClient(@PathVariable String clientId) {
        return service.evaluateClient(clientId);
    }
}

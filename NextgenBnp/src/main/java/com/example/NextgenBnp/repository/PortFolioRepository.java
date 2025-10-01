package com.example.NextgenBnp.repository;

import com.example.NextgenBnp.model.PortFolio;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PortFolioRepository extends MongoRepository<PortFolio, String> {
    Optional<PortFolio> findByClientId(String clientId);
}

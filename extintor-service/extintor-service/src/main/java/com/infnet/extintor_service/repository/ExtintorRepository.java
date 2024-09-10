package com.infnet.extintor_service.repository;


import com.infnet.extintor_service.model.Extintor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ExtintorRepository extends MongoRepository<Extintor, String> {
    Optional<Extintor> findByNumeroControleInterno(int numeroControleInterno);
}

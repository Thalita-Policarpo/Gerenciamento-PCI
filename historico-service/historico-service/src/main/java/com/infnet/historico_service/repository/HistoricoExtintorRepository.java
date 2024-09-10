package com.infnet.historico_service.repository;


import com.infnet.historico_service.model.HistoricoExtintor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HistoricoExtintorRepository extends MongoRepository<HistoricoExtintor, String> {
    List<HistoricoExtintor> findByNumeroControleInterno(int numeroControleInterno);
}

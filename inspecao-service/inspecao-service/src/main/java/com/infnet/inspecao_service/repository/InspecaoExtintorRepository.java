package com.infnet.inspecao_service.repository;


import com.infnet.inspecao_service.model.InspecaoExtintor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface InspecaoExtintorRepository extends MongoRepository<InspecaoExtintor, String> {
    Optional<InspecaoExtintor> findById(String id);
    List<InspecaoExtintor> findByExtintorId(String extintorId);
}

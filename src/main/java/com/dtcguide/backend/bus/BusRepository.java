package com.dtcguide.backend.bus;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusRepository extends MongoRepository<Bus, String> {
    public Optional<Bus> findBusByRouteId(String busNo);
}

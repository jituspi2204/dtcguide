package com.dtcguide.backend.stop;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StopRepository extends MongoRepository<Stop, String> {

    public Optional<Stop> findStopByStopId(String stopId);
}

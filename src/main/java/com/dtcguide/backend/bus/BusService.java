package com.dtcguide.backend.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Bus> getAllBuses(){
        Query query = new Query();
        query.fields().include("bus_no", "route_id", "stops");
        return mongoTemplate.find(query, Bus.class);
    }

    public List<BusMapper> getAllBusesList(){
        Query query = new Query();
        query.fields().include("bus_no", "route_id");
        return mongoTemplate.find(query, BusMapper.class);
    }


    public Bus getBusDetailsByRouteId(String routeId){

        Optional<Bus> bus =  busRepository.findBusByRouteId(routeId);
        return bus.orElse(null);
    }
}


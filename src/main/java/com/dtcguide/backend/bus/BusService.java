package com.dtcguide.backend.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;

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


    public Bus getBusDetailsByRouteId(String routeId){
        return busRepository.findBusByRouteId(routeId).orElseGet(null);
    }
}


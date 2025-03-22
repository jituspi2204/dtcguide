package com.dtcguide.backend.stop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StopService {

    @Autowired
    private StopRepository stopRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Stop> getAllStops(){
        Query query = new Query();
        query.fields().include("stop_id","buses");
        return mongoTemplate.find(query, Stop.class);
    }

    public List<StopMapper> getStopMapper(List<String> stops){
        Query query = Query.query(Criteria.where("stop_id").in(stops));
        System.out.println(stops);
        return mongoTemplate.find(query, StopMapper.class);
    }


    public Stop getStopDetails(String stopId){
        Optional<Stop> optStop = stopRepository.findStopByStopId(stopId);
        return optStop.orElse(null);
    }

    public Map<String, List<Double>> getGeoLocationsForStops(List<String> stops){
        Query query = Query.query(Criteria.where("stop_id").in(stops));
        query.fields().include("stop_lat", "stop_lon", "stop_id");
        List<Stop> result = mongoTemplate.find(query, Stop.class);
        System.out.println(result);
        Map<String, List<Double>> geoMap = new HashMap<>();
        for(Stop stop: result){
            geoMap.put(stop.getStopId(), List.of(stop.getStopLat(), stop.getStopLon()));
        }
        return geoMap;

    }
}

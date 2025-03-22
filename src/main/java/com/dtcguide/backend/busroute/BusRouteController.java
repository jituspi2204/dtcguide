package com.dtcguide.backend.busroute;


import com.dtcguide.backend.bus.BusService;
import com.dtcguide.backend.stop.StopMapper;
import com.dtcguide.backend.stop.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/route")
public class BusRouteController {

    @Autowired
    private StopService stopService;

    @Autowired
    private BusService busService;

    @GetMapping("/path")
    public ResponseEntity<?> getRoutePath(@RequestParam("source") String source,
                                          @RequestParam("destination") String destination){
        System.out.println("called");
        Map<String, List<String>> result = BusRouteGraph.bfsMinStopPath(source, destination);
        List<StopMapper> mapper=  stopService.getStopMapper(result.get("stops"));
        result.get("stops").clear();
        result.put("stops_id", new ArrayList<>());
        for(StopMapper stop : mapper){
            System.out.println(stop.getStopName());
            result.get("stops").add(stop.getStopName());
            result.get("stops_id").add(stop.getStopId());
        }
        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllRoutePath(@RequestParam("source") String source,
                                          @RequestParam("destination") String destination){
        List<Map<String, List<String>>> result = BusRouteGraph.bfsAllStopPath(source, destination);
        for(Map<String, List<String>> route : result){
            List<StopMapper> mapper=  stopService.getStopMapper(route.get("stops"));
            route.get("stops").clear();
            route.put("stops_id", new ArrayList<>());
            for(StopMapper stop : mapper){
                route.get("stops").add(stop.getStopName());
                route.get("stops_id").add(stop.getStopId());
            }

        }
        return  new ResponseEntity<>(result, HttpStatus.OK);
    }







}

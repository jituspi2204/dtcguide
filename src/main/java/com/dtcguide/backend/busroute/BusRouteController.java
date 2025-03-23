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
import java.util.HashMap;
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
        Map<String, List<String>> result = BusRouteGraph.bfsMinStopPath(source, destination);
        if(result.isEmpty()){
            return  new ResponseEntity<>("No route found!!", HttpStatus.NOT_FOUND);
        }
        List<StopMapper> resMapper =  stopService.getStopMapper(result.get("stops_id"));
        Map<String, String> mapper = new HashMap<>();
        for(StopMapper stopMapper : resMapper){
            mapper.put(stopMapper.getStopId(),stopMapper.getStopName());
        }
        result.put("stops", new ArrayList<>());
        for(String stop : result.get("stops_id")){
            result.get("stops").add(mapper.get(stop));
        }
        return  new ResponseEntity<>(result, HttpStatus.OK);
    }









}

package com.dtcguide.backend.stop;


import com.dtcguide.backend.busroute.BusRouteGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController()
@RequestMapping("/api/v1/stop")
public class StopController {

    @Autowired
    private StopService stopService;

    @GetMapping("/details/{stopId}")
    public ResponseEntity<?> getStopDetails(@PathVariable String stopId){
        Stop stop = stopService.getStopDetails(stopId);
        if(stop != null){
            return new ResponseEntity<>(stop, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No stop found for this stop ID" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/geo-locations")
    public ResponseEntity<?> getStopDetails(@RequestBody List<String> stops){
        Map<String, List<Double>> geoLocations = stopService.getGeoLocationsForStops(stops);
        return new ResponseEntity<>(geoLocations, HttpStatus.OK);
    }







}

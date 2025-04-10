package com.dtcguide.backend.bus;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping("/details/{routeId}")
    public ResponseEntity<?> busController(@PathVariable String routeId){
        Bus bus = busService.getBusDetailsByRouteId(routeId);
        if(bus != null){
            return new ResponseEntity<Bus>(bus, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("No bus details found!!", HttpStatus.NOT_FOUND);

        }
    }
    @GetMapping("/list")
    public ResponseEntity<?> getAllBusList(){
        return new ResponseEntity<>(busService.getAllBusesList(), HttpStatus.OK);
    }
}

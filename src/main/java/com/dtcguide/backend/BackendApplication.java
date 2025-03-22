package com.dtcguide.backend;

import com.dtcguide.backend.busroute.BusRouteGraph;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		BusRouteGraph.createGraph();
//		BusRouteGraph.displayBuses();
//		System.out.println(BusRouteGraph.bfsMinStopPath("AnandViharI.S.B.T" , "PalamCrossing"));
	}


}

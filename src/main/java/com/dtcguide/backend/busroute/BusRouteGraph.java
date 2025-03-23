package com.dtcguide.backend.busroute;


import com.dtcguide.backend.bus.Bus;
import com.dtcguide.backend.bus.BusService;
import com.dtcguide.backend.stop.Stop;
import com.dtcguide.backend.stop.StopService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BusRouteGraph {

    @Autowired
    private BusService injectedBusService;

    @Autowired
    private StopService injectedStopService;

    private static BusService busService;
    private static StopService stopService;
    private static HashMap<String, Set<String>> stopToBuses;
    private  static HashMap<String, List<String>> busToStops;

    @PostConstruct
    public void init(){
        busService = injectedBusService;
        stopService = injectedStopService;
    }



    public static void createGraph(){
        List<Bus> buses = busService.getAllBuses();
        List<Stop> stops = stopService.getAllStops();
        stopToBuses = new HashMap<>();
        busToStops= new HashMap<>();
//        System.out.println(buses);
        for(Bus bus : buses){
            List<String> stopArr = new ArrayList<>();
            busToStops.put(bus.getBusNo(), stopArr);
            stopArr.addAll(bus.getStops());
        }

        for(Stop stop : stops){
            Set<String> stopSet = new HashSet<>();
            stopToBuses.put(stop.getStopId() , stopSet);
            if(stop.getBuses() != null){
                stopSet.addAll(stop.getBuses());
            }
        }
    }

    private static class Node{
        String stop;
        String result;

        public Node(String stopId, String result){
            this.stop = stopId;
            this.result = result;
        }
    }


    public static void displayBuses(){
       for(String stop : stopToBuses.keySet()){
           System.out.print(stop + " : ");
           System.out.println(stopToBuses.get(stop));
       }
    }

    public static void displayStops(){
        for(String bus : stopToBuses.keySet()){
            System.out.print(bus + " : ");
            System.out.println(stopToBuses.get(bus));
        }
    }



    public static Map<String, List<String>> bfsMinStopPath(String source, String destination){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(source, ""));
        Set<String> visitedStops = new HashSet<>();
        Set<String> visitedBuses = new HashSet<>();

        visitedStops.add(source);
        while(!queue.isEmpty()){

            Node currentNode = queue.poll();
            String stop = currentNode.stop;
            String result = currentNode.result;
            if(stop.equals(destination)){
//                System.out.print("output : "+result);
                result= result.substring(1);
                String[] arr = result.split("#");
                Map<String, List<String>> path = new HashMap<>();
                path.put("stops_id" , new ArrayList<>());
                path.put("buses" , new ArrayList<>());
                List<String> changeBuses = new ArrayList<>();
                List<String> changeStops = new ArrayList<>();
                for (String s : arr) {
                    String[] change = s.split("@");
                    changeStops.add(change[0]);
                    changeBuses.add(change[1]);
                }
//                System.out.println(changeStops);
//                System.out.println(changeBuses);

                changeStops.add(destination);

                for(int i = 0; i < changeBuses.size();i++){
                    int startIdx = busToStops
                            .get(changeBuses.get(i))
                            .indexOf(changeStops.get(i));
                    int endIdx = busToStops
                            .get(changeBuses.get(i))
                            .indexOf(changeStops.get(i + 1));
                    for(int j = startIdx; j <= endIdx;j++){
                        path.get("stops_id").add(busToStops.get(changeBuses.get(i)).get(j) );
                        path.get("buses").add(changeBuses.get(i));
                    }
                }
                return path;
            }

            //explore all buses from current stop
            for(String bus : stopToBuses.getOrDefault(stop, new HashSet<>())){
                if(visitedBuses.contains(bus)) continue;
                visitedBuses.add(bus);
                int idx = busToStops
                        .get(bus)
                        .indexOf(stop);
                if(idx == -1) continue;
                for(int i = idx; i < busToStops.get(bus).size();i++){
                    String nextStop = busToStops.get(bus).get(i);
                    if(!visitedStops.contains(nextStop)){
                        visitedStops.add(nextStop);
                        queue.add(new Node(nextStop,
                                result + "#"  + stop + "@" + bus));
                    }
                }

            }


        }
        return new HashMap<>();
    }



    public static List<Map<String, List<String>>>
        bfsAllStopPath(String source, String destination){
        return null;
    }

}

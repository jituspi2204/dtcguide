package com.dtcguide.backend.stop;

import jakarta.annotation.PostConstruct;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "stops")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Stop {
    @Id
    private String id;
    @Field("stop_lat")
    private Double stopLat;
    @Field("stop_lon")
    private Double stopLon;
    @Field("stop_id")
    private String stopId;
    @Field("stop_name")
    private String stopName;
    private List<String> buses;
}

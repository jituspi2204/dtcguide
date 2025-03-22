package com.dtcguide.backend.bus;

import jakarta.annotation.PostConstruct;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "buses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Bus {

    @Id
    private String id;
    private String start;
    private String end;
    @Field("route_id")
    private String routeId;
    @Field("bus_no")
    private String busNo;
    @Field("stops")
    private List<String> stops;

}

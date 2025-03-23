package com.dtcguide.backend.bus;

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
public class BusMapper {
    @Id
    private String id;
    @Field("route_id")
    private String routeId;
    @Field("bus_no")
    private String busNo;
}
package com.dtcguide.backend.stop;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "stops_map")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StopMapper {
    @Id
    private String id;
    @Field("stop_id")
    private String stopId;
    @Field("stop_name")
    private String stopName;
}